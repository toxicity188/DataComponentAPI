tasks {
    jar {
        finalizedBy(shadowJar)
    }
    shadowJar {
        archiveClassifier = ""
        manifest {
            attributes["paperweight-mappings-namespace"] = "spigot"
        }
    }
}