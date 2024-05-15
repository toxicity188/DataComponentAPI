plugins {
    id("java")
    id("io.github.goooler.shadow") version("8.1.7")
    id("io.papermc.paperweight.userdev") version("1.7.1") apply(false)
    id("xyz.jpenilla.run-paper") version("2.3.0")
    `maven-publish`
}


allprojects {
    apply(plugin = "java")

    group = "kr.toxicity.libraries.datacomponent"
    version = "1.0.6"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.32")
        annotationProcessor("org.projectlombok:lombok:1.18.32")

        testCompileOnly("org.projectlombok:lombok:1.18.32")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.32")

        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks {
        compileJava {
            dependsOn(clean)
            options.compilerArgs.addAll(listOf("-source", "17", "-target", "17"))
            options.encoding = Charsets.UTF_8.name()
        }
        test {
            useJUnitPlatform()
        }
    }

    java {
        toolchain.languageVersion = JavaLanguageVersion.of(21)
    }
}

fun Project.dependency(dependency: Any) = also {
    it.dependencies {
        compileOnly(dependency)
        testImplementation(dependency)
    }
}

fun Project.paper() = dependency("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
fun Project.paperweight() = also {
    it.apply(plugin = "io.papermc.paperweight.userdev")
}
fun Project.shadowJar() = also {
    it.apply(plugin = "io.github.goooler.shadow")
}
fun Project.runPaper() = also {
    it.apply(plugin = "xyz.jpenilla.run-paper")
}

val api = project("api").paper()

val dist = project("dist").paper().dependency(api)

val nms = listOf(
    project("nms:v1_20_R4").paperweight(),
)

nms.forEach {
    it.dependency(api)
    dist.dependency(it)
}


fun Project.fatJar() = also {
    it.dependencies {
        implementation(api)
        implementation(dist)
        nms.forEach { p ->
            implementation(project(":nms:${p.name}", configuration = "reobf"))
        }
    }
}

listOf(
    project("test-plugin:library").runPaper(),
    project("test-plugin:shade").runPaper().shadowJar()
).forEach {
    it.paper()
        .fatJar()
        .tasks {
            runServer {
                runDirectory(File(rootProject.projectDir, "run"))
                dependsOn(rootProject.tasks.build)
                version("1.20.6")
            }
    }
}

rootProject.fatJar()

tasks {
    jar {
        finalizedBy(shadowJar)
    }
    shadowJar {
        manifest {
            attributes["paperweight-mappings-namespace"] = "spigot"
        }
        archiveClassifier = ""
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("shadow") {
                project.extensions.configure<com.github.jengelman.gradle.plugins.shadow.ShadowExtension> {
                    component(this@create)
                }
            }
        }
    }
}