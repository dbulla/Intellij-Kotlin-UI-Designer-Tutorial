package com.nurflugel.tutorial

import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.SwingUtilities
import kotlin.system.exitProcess

class KotlinDemoUiIntellijUiDesigner : DemoUiIntellijUiDesigner() {
    private var theFrame: JFrame = JFrame()

    init {
        addListeners()
        theFrame.contentPane = panel1
        theFrame.pack()
        theFrame.isVisible = true
    }

    private fun addListeners() {
        okButton.addActionListener {
            JOptionPane.showMessageDialog(theFrame, "You typed ${(firstTextField.text + " " + secondTextField.text).trim().ifEmpty { "nothing" }} then clicked OK!")
        }
        cancelButton.addActionListener { exitProcess(0) }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeLater {
                KotlinDemoUiIntellijUiDesigner()
            }
        }
    }

}