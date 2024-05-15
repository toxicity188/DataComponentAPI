tasks {
    runServer {
        rootProject.layout.buildDirectory.dir("libs").map { d ->
            d.asFile
        }.orNull?.listFiles()?.firstOrNull { f ->
            f.nameWithoutExtension.endsWith(project.version.toString())
        }?.let { jar ->
            pluginJars(jar)
        }
    }
    jar {
        manifest {
            attributes["paperweight-mappings-namespace"] = "spigot"
        }
    }
}