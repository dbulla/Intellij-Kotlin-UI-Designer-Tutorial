# Intellij-Kotlin-UI-Designer-Tutorial

## Overview
This is a tutorial on how to use JetBrain's UI designer in a Kotlin/Gradle dev environment.

The Java-based UI designer is a powerful tool that allows developers to create user interfaces for 
their applications without having to write any code. It provides a visual interface for 
designing layouts, adding components, and configuring their properties.

In this tutorial, we will walk you through the process of setting up the UI designer in a 
Kotlin/Gradle development environment and creating a simple user interface.

Normally, the JetBrains UI Designer only supports Java, and if you're a Kotlin fan like myself, that's limiting.  However,
the workaround is really pretty simple - since Gradle can host both Java and Kotlin in the same project, we let IDEA create the UI
in the Java source directory, and then create a Kotlin superclass in the Kotlin source directory.  Presto, chango - 
you're all set!

OK, there's a little more to it than that, but we'll get to that in the tutorial.

## Why?  What's the point?
I write UI apps, and I like the Kotlin language.  I also like the JetBrains UI Designer - but it doesn't support Kotlin.
By using that to create my UI in Java, I can then inherit the Java UI classes in my Kotlin code.  This gives a really nice, 
clean separation of concerns between the UI and the business logic.  The 
Java code has all the visual layout, and the Kotlin code has all the business logic, without any of the UI cruft.

## Tutorial

### UI Designer Settings
First, we need to configure IDEA's UI designer to work with Kotlin/Gradle projects - here's the setting:

![ui designer editor settings](./assets/idea_ui_settings.png)

Note that rather than the default of having the UI designer inject code into the class files, 
we want it to explicitly "Generate Java in the source code on save".

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

Gradle already deals with mixed languages, so we don't need to do anything special to support Kotlin and Java in the same project.

### Code

#### What does our original code look like?
In the src/main/kotlin directory, there are 2 very (very) simple UIs done in Kotlin.   

The first, ["KotlinDemoUIOOOOld"](../src/main/kotlin/com/nurflugel/tutorial/KotlinDemoUIOOOOld.kt) is the UI done with 
GridBagLayout - it's all boilerplate, and almost no important code other than the listeners.

<details>
  <summary>Click to expand file contents</summary>

  <!-- IMPORTANT: Leave an empty line above this comment -->

    ```kotlin
    package com.nurflugel.tutorial
    
    import java.awt.Dimension
    import java.awt.Font
    import java.awt.Font.BOLD
    import java.awt.GridBagConstraints
    import java.awt.GridBagConstraints.BOTH
    import java.awt.GridBagConstraints.EAST
    import java.awt.GridBagConstraints.HORIZONTAL
    import java.awt.GridBagConstraints.VERTICAL
    import java.awt.GridBagConstraints.WEST
    import java.awt.GridBagLayout
    import javax.swing.JButton
    import javax.swing.JFrame
    import javax.swing.JLabel
    import javax.swing.JPanel
    import javax.swing.JTextField
    import javax.swing.SwingUtilities
    import javax.swing.plaf.FontUIResource
    import javax.swing.text.StyleContext
    
    class KotlinDemoUIOOOOld: JFrame() {
        private var demoLabel: JLabel
        private var firstTextField: JTextField
        private var secondTextField: JTextField
        private var okButton: JButton
        private var cancelButton: JButton
    
        companion object {
            @JvmStatic
            fun main(args: Array<String>) {
                SwingUtilities.invokeLater {
                    val frame = KotlinDemoUIOOOOld()
                    frame.pack()
                    frame.isVisible = true
                }
            }
        }
    
        init {
            val mainPanel = JPanel()
    
            mainPanel.setLayout(GridBagLayout())
            demoLabel = JLabel()
            val demoLabelFont = createFont(null, BOLD, 28, demoLabel.getFont())
            if (demoLabelFont != null) demoLabel.setFont(demoLabelFont)
            demoLabel.setText("Demo UI Application")
    
            var gbc = GridBagConstraints()
            gbc.gridx = 1
            gbc.gridy = 0
            gbc.gridwidth = 3
            gbc.anchor = WEST
            mainPanel.add(demoLabel, gbc)
            val spacer1 = JPanel()
            gbc = GridBagConstraints()
            gbc.gridx = 4
            gbc.gridy = 0
            gbc.fill = HORIZONTAL
            mainPanel.add(spacer1, gbc)
            val spacer2 = JPanel()
            gbc = GridBagConstraints()
            gbc.gridx = 3
            gbc.gridy = 1
            gbc.fill = VERTICAL
            mainPanel.add(spacer2, gbc)
            firstTextField = JTextField()
            firstTextField.minimumSize = Dimension(100, 30)
            firstTextField.preferredSize = Dimension(100, 30)
            gbc = GridBagConstraints()
            gbc.gridx = 2
            gbc.gridy = 1
            gbc.anchor = WEST
            gbc.fill = HORIZONTAL
            mainPanel.add(firstTextField, gbc)
            val label1 = JLabel()
            label1.setText("Second field:")
            gbc = GridBagConstraints()
            gbc.gridx = 1
            gbc.gridy = 2
            gbc.anchor = EAST
            mainPanel.add(label1, gbc)
            secondTextField = JTextField()
            gbc = GridBagConstraints()
            gbc.gridx = 2
            gbc.gridy = 2
            gbc.anchor = EAST
            gbc.fill = HORIZONTAL
            mainPanel.add(secondTextField, gbc)
            val label2 = JLabel()
            label2.setText("First field:")
            gbc = GridBagConstraints()
            gbc.gridx = 1
            gbc.gridy = 1
            gbc.anchor = EAST
            mainPanel.add(label2, gbc)
            val panel2 = JPanel()
            panel2.setLayout(GridBagLayout())
            gbc = GridBagConstraints()
            gbc.gridx = 2
            gbc.gridy = 4
            gbc.fill = BOTH
            mainPanel.add(panel2, gbc)
            okButton = JButton()
            okButton.setText("OK")
            gbc = GridBagConstraints()
            gbc.gridx = 0
            gbc.gridy = 0
            gbc.fill = HORIZONTAL
            panel2.add(okButton, gbc)
            val spacer3 = JPanel()
            gbc = GridBagConstraints()
            gbc.gridx = 0
            gbc.gridy = 1
            gbc.fill = VERTICAL
            panel2.add(spacer3, gbc)
            cancelButton = JButton()
            cancelButton.setText("Cancel")
            gbc = GridBagConstraints()
            gbc.gridx = 1
            gbc.gridy = 0
            gbc.fill = HORIZONTAL
            panel2.add(cancelButton, gbc)
            val spacer4 = JPanel()
            gbc = GridBagConstraints()
            gbc.gridx = 2
            gbc.gridy = 3
            gbc.fill = VERTICAL
            mainPanel.add(spacer4, gbc)
            val spacer5 = JPanel()
            gbc = GridBagConstraints()
            gbc.gridx = 0
            gbc.gridy = 0
            gbc.fill = HORIZONTAL
            mainPanel.add(spacer5, gbc)
    
            contentPane = mainPanel
            defaultCloseOperation = EXIT_ON_CLOSE
        }
    
        @Suppress("SameParameterValue")
        private fun createFont(fontName: String?, style: Int, size: Int, currentFont: Font?): Font? {
            if (currentFont == null) return null
            val resultName: String = if (fontName == null) {
                currentFont.getName()
            }
            else {
                val testFont = Font(fontName, Font.PLAIN, 10)
                if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                    fontName
                }
                else {
                    currentFont.getName()
                }
            }
            val font = Font(resultName, if (style >= 0) style else currentFont.getStyle(), if (size >= 0) size else currentFont.getSize())
            val isMac = System.getProperty("os.name", "").lowercase().startsWith("mac")
            val fontWithFallback = if (isMac) Font(font.family, font.getStyle(), font.getSize()) else StyleContext().getFont(font.family, font.getStyle(), font.getSize())
            return fontWithFallback as? FontUIResource
                    ?: FontUIResource(fontWithFallback)
        }
    }
    
    ```

</details>



