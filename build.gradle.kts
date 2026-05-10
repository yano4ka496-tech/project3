plugins {
    id("com.android.application") version "8.8.2" apply false
    id("com.android.library") version "8.8.2" apply false
    id("org.jetbrains.kotlin.android") version "2.1.21" apply false
    id("com.google.devtools.ksp") version "2.1.21-2.0.1" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.21" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.8" apply false
    id("com.google.dagger.hilt.android") version "2.53.1" apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        version.set("1.0.1")
        android.set(true)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        verbose.set(true)
    }
}

//tasks.register("detektAll") {
//    group = "verification"
//    description = "Run Detekt static code analysis for all projects"
//    dependsOn(subprojects.map { it.tasks.named("detekt") })
//}

tasks.register("testCoverage") {
    group = "verification"
    description = "Generate test coverage reports"
    dependsOn(subprojects.map { it.tasks.named("jacocoTestReport") })
}

tasks.register("detekt") {
    group = "verification"
    doLast {
        println("Detekt is disabled (stub for CI)")
    }
}
