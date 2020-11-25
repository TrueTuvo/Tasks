package swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BottomPanel extends JPanel {

    public static final String ZERO_VALUE = "0";

    private static final long serialVersionUID = 1L;

    private final JLabel simpleLabel;
    private final JTextArea result;

    public BottomPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setLayout(new GridBagLayout());

        simpleLabel = new JLabel();
        simpleLabel.setText("Result : ");
        result = new JTextArea();
        result.setEnabled(false);
        result.setText(ZERO_VALUE);

        add(simpleLabel);
        add(result, gbc);

    }

    public JTextArea getResult() {
        return result;
    }
}
