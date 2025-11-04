pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        // repo necesario para el plugin/artefactos de JetBrains Compose
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

// ...existing code...
rootProject.name = "tp3_parcial_grupal3"
include(":app")