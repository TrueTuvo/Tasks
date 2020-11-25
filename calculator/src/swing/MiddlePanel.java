package swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class MiddlePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    JButton calculateButton;
    JCheckBox isCOTF;

    private final String nameCheckedButton = "Calculate on the fly";
    private final String namePressedButton = "Calculate";

    public MiddlePanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setLayout(new GridBagLayout());

        calculateButton = new JButton();
        calculateButton.setText(namePressedButton);
        isCOTF = new JCheckBox();
        isCOTF.setText(nameCheckedButton);

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
