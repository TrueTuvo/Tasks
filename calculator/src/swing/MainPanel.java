package swing;


import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



import calculator.Calculators;
import calculator.MathSignEnum;



public class MainPanel extends JTabbedPane{
    
    public static final String MATH_EXCEPTION_MESSAGE = "Arithmetic Error";
    private static final String ZERO_VALUE = "0";

    private final JTextField firstNumber;
    private final JComboBox<MathSignEnum> mathSign;
    private final JTextField secondNumber;
    private final JCheckBox isCOTF; // COTF - Calculate on the fly.
    private final JButton calculateButton;
    private final JTextArea result;

    private BigDecimal firstValue;
    private BigDecimal secondValue;
    private BigDecimal finalResult = new BigDecimal(0);
    private String calculatedLineString = "";
    

    private static final long serialVersionUID = 1L;

    public MainPanel() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(1);
        gridLayout.setRows(3);
        JPanel mainJPanel = new JPanel();
        mainJPanel.setLayout(gridLayout);
                
        addTab("Calculator",mainJPanel);
        
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> historyList = new JList<String>(model);
        addTab("History", historyList);
        TopPanel inputValues = new TopPanel();
        MiddlePanel buttons = new MiddlePanel();
        BottomPanel results = new BottomPanel();
        
        mainJPanel.add(inputValues);
        mainJPanel.add(buttons);
        mainJPanel.add(results);

        
        firstNumber = inputValues.getFirstNumber();
        mathSign = inputValues.getMathSign();
        secondNumber = inputValues.getSecondNumber();
        isCOTF = buttons.getIsCOTF();
        calculateButton = buttons.getCalculateButton();
        result = results.getResult();
        
        ActionListener isCOTFListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                 JCheckBox jCheckBox = (JCheckBox)arg0.getSource();
                     calculateButton.setEnabled(!jCheckBox.isEnabled());
            
            }
        };
        
        
        KeyListener calculateButtonListener = new KeyListener() {


            @Override
            public void keyPressed(KeyEvent arg0) {
                execute(historyList);
                
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void keyTyped(KeyEvent arg0) {
                // TODO Auto-generated method stub
                
            }
        };
        
        ActionListener inputModifyListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (isCOTF.isEnabled()) {
                    execute(historyList);
                }
                
            }
        };
       
        
        calculateButton.addActionListener(isCOTFListener);
        
        isCOTF.addActionListener(isCOTFListener);

        mathSign.addActionListener(inputModifyListener);
        firstNumber.addActionListener(inputModifyListener);
        secondNumber.addActionListener(inputModifyListener);


    }
    
    private void execute(JList<String> resultsLog) {
        
        MathSignEnum currentOperation = (MathSignEnum)mathSign.getSelectedItem();
        try {
            firstValue = new BigDecimal(firstNumber.getText());
            secondValue = new BigDecimal(secondNumber.getText());
        } catch (Exception e2) {
            firstValue = new BigDecimal(ZERO_VALUE);
            secondValue = new BigDecimal(ZERO_VALUE);
        }
        if (Calculators.isValidate(firstValue.toString(), secondValue.toString(), currentOperation)) {
            finalResult = Calculators.calculate(firstValue, secondValue, currentOperation);
            calculatedLineString = Calculators.createCalculatedLine(firstValue, secondValue, currentOperation,
                    finalResult);
            result.setText(finalResult.toString());
            resultsLog.add(new Label(calculatedLineString));
        } else {
            result.setText(MATH_EXCEPTION_MESSAGE);
        }
    }
    
}
