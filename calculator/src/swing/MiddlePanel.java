package swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import calculator.Calculators;

public class MiddlePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final JButton calculateButton;
    private final JCheckBox isCOTF;

    public MiddlePanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setLayout(new GridBagLayout());

        calculateButton = new JButton();
        calculateButton.setText(Calculators.NAME_PRESSED_BUTTON);
        isCOTF = new JCheckBox();
        isCOTF.setText(Calculators.NAME_CHECKED_BUTTON);

        add(isCOTF, gbc);
        add(calculateButton, gbc);
    }

    public JCheckBox getIsCOTF() {
        return isCOTF;
    }

    public JButton getCalculateButton() {
        return calculateButton;
    }
}
