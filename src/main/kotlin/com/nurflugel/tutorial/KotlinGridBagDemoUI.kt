package com.nurflugel.tutorial

import java.awt.Dimension
import java.awt.Font
import java.awt.Font.BOLD
import java.awt.GridBagConstraints
import java.awt.GridBagConstraints.*
import java.awt.GridBagLayout
import javax.swing.*
import javax.swing.plaf.FontUIResource
import javax.swing.text.StyleContext
import kotlin.system.exitProcess

class KotlinGridBagDemoUI : JFrame() {
    private var demoLabel: JLabel
    private var firstTextField: JTextField
    private var secondTextField: JTextField
    private var okButton: JButton
    private var cancelButton: JButton

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

        okButton.addActionListener {
            JOptionPane.showMessageDialog(this, "You typed ${(firstTextField.text + "  " + secondTextField.text).trim().ifEmpty { "nothing" }} then clicked OK!")
        }
        cancelButton.addActionListener { exitProcess(0) }
    }

    @Suppress("SameParameterValue")
    fun createFont(fontName: String?, style: Int, size: Int, currentFont: Font?): Font? {
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

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeLater {
                val frame = KotlinGridBagDemoUI()
                frame.pack()
                frame.isVisible = true
            }
        }
    }
}

