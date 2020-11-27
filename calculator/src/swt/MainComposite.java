package swt;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import calculator.Calculators;
import calculator.MathSignEnum;

public class MainComposite extends Composite {

    private final Text firstNumber;
    private final Combo mathSign;
    private final Text secondNumber;
    private final Button isCOTF; // COTF - Calculate on the fly.
    private final Button calculateButton;
    private final Text result;

    private BigDecimal firstValue;
    private BigDecimal secondValue;
    private BigDecimal finalResult = new BigDecimal(0);
    private String calculatedLineString = ""; // using for view history

    /**
     * Create the composite from several part, add listeners, and init all fields
     * 
     * @param parent sets the parent widget
     * @param style set style
     */
    public MainComposite(Composite parent, int style) {
        super(parent, style);
        setLayout(new FillLayout(SWT.HORIZONTAL));

        CTabFolder folder = new CTabFolder(parent, SWT.TOP);
        GridData data = new GridData(SWT.FILL, SWT.FILL, false, false);

        folder.setLayoutData(data);
        folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        folder.setLayout(new GridLayout());

        CTabItem calculatorPage = new CTabItem(folder, SWT.NONE);
        calculatorPage.setText("Calculator");

        CTabItem historyPage = new CTabItem(folder, SWT.NONE);
        historyPage.setText("History");

        List historyList = new List(folder, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
        historyList.setLayoutData(new GridData(1));

        Composite composite = new Composite(folder, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        composite.setLayout(new GridLayout(1, false));

        TopComposite inputValues = new TopComposite(composite, SWT.NONE);
        GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        inputValues.setLayoutData(gridData);

        MiddleComposite buttons = new MiddleComposite(composite, SWT.NONE);
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, true);
        gridData.verticalIndent = 70;
        buttons.setLayoutData(gridData);

        BottomComposite results = new BottomComposite(composite, SWT.NONE);
        gridData = new GridData(SWT.FILL, SWT.CENTER, true, true);
        gridData.verticalIndent = 50;
        results.setLayoutData(gridData);

        firstNumber = inputValues.getFirstNumber();
        mathSign = inputValues.getMathSign();
        secondNumber = inputValues.getSecondNumber();
        isCOTF = buttons.getIsCOTF();
        calculateButton = buttons.getCalculateButton();
        result = results.getResult();

        SelectionAdapter isCOTFListener = new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Button button = (Button) e.widget;
                boolean isButtonEnebled = !button.getSelection();
                calculateButton.setEnabled(isButtonEnebled);
            }
        };

        SelectionAdapter calculateButtonListener = new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                execute(historyList);
            }
        };

        ModifyListener inputModifyListener = new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                if (isCOTF.getSelection()) {
                    execute(historyList);
                }
            }
        };

        calculateButton.addSelectionListener(calculateButtonListener);
        isCOTF.addSelectionListener(isCOTFListener);

        mathSign.addModifyListener(inputModifyListener);
        firstNumber.addModifyListener(inputModifyListener);
        secondNumber.addModifyListener(inputModifyListener);

        calculatorPage.setControl(composite);
        historyPage.setControl(historyList);
    }

    /**
     * iterates over all available types of mathematical operations and finds a match with the current
     * 
     * 
     * @return returns the type of a mathematical operation
     */
    private MathSignEnum getCurrentOperation() {
        for (MathSignEnum element : MathSignEnum.values()) {
            if (element.ordinal() == mathSign.getSelectionIndex()) {
                return element;
            }
        }
        return null;
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
    private void execute(List resultsLog) {
        MathSignEnum currentOperation = getCurrentOperation();
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
            resultsLog.add(calculatedLineString);
        } else {
            result.setText(Calculators.MATH_EXCEPTION_MESSAGE);
        }
    }
}