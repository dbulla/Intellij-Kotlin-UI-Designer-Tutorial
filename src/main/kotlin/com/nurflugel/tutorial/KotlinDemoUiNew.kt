package com.nurflugel.tutorial

import javax.swing.JFrame
import javax.swing.SwingUtilities
import kotlin.system.exitProcess

class KotlinDemoUiNew : DemoUiNew() {
    private var frame: JFrame = JFrame()

    init {
        addListeners()
        frame.contentPane = panel1
        frame.pack()
        frame.isVisible = true
    }

    private fun addListeners() {
        okButton.addActionListener { println("Doing something on OK pressed") }
        cancelButton.addActionListener {
            println("Doing something on cancel pressed")
            exitProcess(0)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeLater {
                val frame = KotlinDemoUiNew()
            }
        }
    }

}