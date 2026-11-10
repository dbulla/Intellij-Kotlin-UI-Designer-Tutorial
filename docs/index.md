# Intellij-Kotlin-UI-Designer-Tutorial

## Overview
This is a tutorial on how to use JetBrain's UI designer in a Kotlin/Gradle dev environment.

The UI designer is a powerful tool that allows developers to create user interfaces for 
their applications without having to write any code. It provides a visual interface for 
designing layouts, adding components, and configuring their properties. 

In this tutorial, we will walk you through the process of setting up the UI designer in a 
Kotlin/Gradle development environment and creating a simple user interface.

Normally, the JetBrains UI Designer only supports Java, and if you're a Kotlin fan like myself, that's limiting.  However,
the workaround is really pretty simple - since Gradle can host both Java and Kotlin in the same project, we let IDEA create the UI
in the Java source directory, and then create a Kotlin superclass in the Kotlin source directory.  Presto, chango - 
you're all set!

OK, there's a little more to it than that, but we'll get to that in the tutorial.

## Tutorial

### UI Designer Settings
First, we need to configure IDEA's UI designer to work with Kotlin/Gradle projects:

[ui designer editor settings](./assets/idea_ui_settings.png)

Note that rather than the default of having the UI designer inject code into the class files, we want it to explicitly generate the Java code in the class.

### Gradle

To configure Gradle to work with Kotlin and the JetBrains UI Designer, we need to add the following dependencies to our `build.gradle.kts` file:

```kotlin
plugins {
    application
    kotlin("jvm") version "2.4.21"
}
dependencies {
        implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
        implementation("com.intellij:forms_rt:7.0.3")
}
```
The important line here is adding the dependency to the JetBrains `forms_rt` library.




