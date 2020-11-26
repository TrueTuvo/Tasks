package swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import calculator.Calculators;
import calculator.MathSignEnum;

public class MainPanel extends JTabbedPane {

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

    /**
     * Create the composite from several part, add listeners, and init all fields
     * 
     */
    public MainPanel() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(1);
        gridLayout.setRows(3);
        JPanel mainJPanel = new JPanel();
        mainJPanel.setLayout(gridLayout);

        addTab("Calculator", mainJPanel);

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
                calculateButton.setEnabled(!isCOTF.isSelected());

            }
        };

        ActionListener calculateButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                execute(model);

            }
        };
        ActionListener mathSignListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (isCOTF.isSelected() || calculateButton.isSelected()) {
                    execute(model);
                }

            }
        };

        DocumentListener inputModifyListener = new DocumentListener() {

            public void doAction() {
                if (isCOTF.isSelected() || calculateButton.isSelected()) {
                    execute(model);
                }

            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                doAction();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                doAction();

            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                doAction();

            }
        };

        calculateButton.addActionListener(calculateButtonListener);

        isCOTF.addActionListener(isCOTFListener);

        mathSign.addActionListener(mathSignListener);
        firstNumber.getDocument().addDocumentListener(inputModifyListener);
        secondNumber.getDocument().addDocumentListener(inputModifyListener);
    }

    /**
     * This method does the following: it initially determines the type of operation, and then initializes both values;
     * if the input field is empty, it will be initialized to zero. Then the mathematical operation is validated. If the
     * result is received, then: result will be displayed in the result field; mathematical expression will be added to
     * the calculation history.
     * 
     * 
     * @param resultsLog this variable will be added mathematical expression
     */
    private void execute(DefaultListModel<String> resultsLog) {

        MathSignEnum currentOperation = (MathSignEnum) mathSign.getSelectedItem();
        try {
            firstValue = new BigDecimal(firstNumber.getText());
            secondValue = new BigDecimal(secondNumber.getText());
        } catch (Exception e2) {
            firstValue = new BigDecimal(Calculators.ZERO_VALUE);
            secondValue = new BigDecimal(Calculators.ZERO_VALUE);
        }
        if (Calculators.isValidate(firstValue.toString(), secondValue.toString(), currentOperation)) {
            finalResult = Calculators.calculate(firstValue, secondValue, currentOperation);
            calculatedLineString = Calculators.createCalculatedLine(firstValue, secondValue, currentOperation,
                    finalResult);
            result.setText(finalResult.toString());
            resultsLog.addElement(calculatedLineString);
        } else {
            result.setText(Calculators.MATH_EXCEPTION_MESSAGE);
        }
    }

}
