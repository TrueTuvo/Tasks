package calculator;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swing.CalculatorSwing;
import swt.CalculatorSWT;

public class Main {
    public static void main(String[] args) {

        JFrame chouseCalculatorWindow = new JFrame();
        chouseCalculatorWindow.setVisible(true);
        chouseCalculatorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        chouseCalculatorWindow.setBounds(dimension.width / 2 - dimension.width / 8,
                dimension.height / 2 - dimension.width / 8, dimension.width / 4, dimension.width / 10);
        chouseCalculatorWindow.setTitle("calculator");
        chouseCalculatorWindow.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel jPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jPanel.setLayout(new GridBagLayout());

        JButton swingButton = new JButton();
        swingButton.setText("SWING");
        JButton swtButton = new JButton();
        swtButton.setText("SWT");

        jPanel.add(swingButton, gbc);
        jPanel.add(swtButton, gbc);

        JLabel questionLabel = new JLabel();
        questionLabel.setText("What type of calculator should you run?");

        ActionListener ButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JButton jButton = (JButton) arg0.getSource();
                if (jButton.getText().equals("SWING")) {
                    CalculatorSwing calculatorSwing = new CalculatorSwing();
                    chouseCalculatorWindow.dispose();
                    calculatorSwing.init();
                } else {
                    CalculatorSWT calculatorSWT = new CalculatorSWT();
                    chouseCalculatorWindow.dispose();
                    calculatorSWT.init();
                }

            }
        };

        swingButton.addActionListener(ButtonListener);
        swtButton.addActionListener(ButtonListener);

        chouseCalculatorWindow.add(questionLabel);
        chouseCalculatorWindow.add(jPanel);

    }
}
