package swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class CalculatorSwing extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        CalculatorSwing calculatorSwing = new CalculatorSwing();
    }

    public CalculatorSwing() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width / 2 - dimension.width / 8, dimension.height / 2 - dimension.width / 8,
                dimension.width / 4, dimension.width / 4);
        setTitle("SWING calculator");
        setLayout(new GridLayout());
        add(new MainPanel());

    }
}
