pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SafePlant"
include(":app")
include(":core-navigation")
include(":core-database")
include(":core-storage")
include(":core-security")
include(":core-mapping")
include(":core-utils")
include(":feature-quiz")
include(":feature-map")
include(":feature-training")
include(":feature-qr")
include(":feature-profile")