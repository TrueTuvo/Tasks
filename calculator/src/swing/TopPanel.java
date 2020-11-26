package swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calculator.Calculators;
import calculator.MathSignEnum;

public class TopPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextField firstNumber, secondNumber;
    private final JComboBox<MathSignEnum> mathSign;


    public TopPanel() {

        KeyAdapter keyAdapter = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                JTextField jTextField = (JTextField) e.getSource();

                String value = jTextField.getText() + e.getKeyChar();

                if (isCorect(value)) {
                    jTextField.setEditable(true);
                    if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == '.'
                            || e.getKeyChar() == '-' || e.getKeyChar() == 8) {
                        jTextField.setEditable(isCorect(value));

                    }
                } else {
                    jTextField.setEditable(isCorect(value));

                }
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setLayout(new GridBagLayout());

        firstNumber = new JTextField();
        firstNumber.setText(Calculators.ZERO_VALUE);

        secondNumber = new JTextField();
        secondNumber.setText(Calculators.ZERO_VALUE);

        mathSign = new JComboBox<MathSignEnum>();
        mathSign.addItem(MathSignEnum.SUBTRACT);
        mathSign.addItem(MathSignEnum.MULTIPLY);
        mathSign.addItem(MathSignEnum.DIVIDE);
        mathSign.addItem(MathSignEnum.ADD);

        firstNumber.addKeyListener(keyAdapter);
        secondNumber.addKeyListener(keyAdapter);

        add(firstNumber, gbc);
        add(mathSign);
        add(secondNumber, gbc);

    }

    private boolean isCorect(String text) {
        if (text.equals(Calculators.STRING_VALUE_DOT) || text.equals(Calculators.STRING_VALUE_SUBSTRACTION) || text.equals(Calculators.EMPTY_STRING)) {
            return true;
        }
        try {
            Float.parseFloat(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public JTextField getFirstNumber() {
        return firstNumber;
    }

    public JComboBox<MathSignEnum> getMathSign() {
        return mathSign;
    }

    public JTextField getSecondNumber() {
        return secondNumber;
    }
}
