import org.gradle.internal.jvm.Jvm

val kotlinVersion = "2.4.21"
val javaVersion = 25

plugins {
    // Application plugin to add support for building a CLI application in Java.
    application
    kotlin("jvm") version "2.4.21"
}

repositories {
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("com.intellij:forms_rt:7.0.3")
}

application {
    // Define the main class for the application.
    mainClass.set("com.nurflugel.tutorial.UiDesignerDemo")
    group = "com.nurflugel"
    version = "0.0.1-SNAPSHOT"
}

kotlin {
    jvmToolchain(javaVersion)
}

println(
    """
             ========================================================================================================
             Welcome to:                         ${project.name}
             Gradle version:                     ${project.gradle.gradleVersion}
             Java version:                       ${Jvm.current()}
             Java home:                          ${Jvm.current().javaHome}
             Gradle user directory is set to:    ${project.gradle.gradleUserHomeDir}
             Project directory:                  ${project.projectDir}
             Running build script:               ${project.buildFile}
             Subprojects:                        ${project.subprojects.map { it.name }}
             """.trimIndent()
)
