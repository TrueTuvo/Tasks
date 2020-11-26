package swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import calculator.Calculators;
import calculator.MathSignEnum;

public class TopComposite extends Composite {
    private final Text firstNumber;
    private final Combo mathSign;
    private final Text secondNumber;

    public TopComposite(Composite parent, int style) {
        super(parent, style);

        VerifyListener inputNuberVerifyListener = new VerifyListener() {

            @Override
            public void verifyText(VerifyEvent e) {

                String newS = Calculators.EMPTY_STRING;

                boolean isFloat = true;

                Text text = (Text) e.getSource();

                final String oldS = text.getText();

                if (e.keyCode == Calculators.CHAR_VALUE_SUBSTRACTION) {
                    if (text.getText().length() < 1) {
                        e.text = Calculators.STRING_VALUE_SUBSTRACTION;
                        newS = Calculators.ZERO_VALUE;
                    } else {
                        newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);
                    }
                } else if (e.keyCode == SWT.BS) {
                    if (oldS.length() == text.getSelectionText().length()) {
                        e.text = Calculators.EMPTY_STRING;
                        newS = Calculators.ZERO_VALUE;
                    } else if (oldS.length() == 1) {
                        e.text = Calculators.EMPTY_STRING;
                        newS = Calculators.ZERO_VALUE;

                    } else if (oldS.length() == 2 && oldS.startsWith(Calculators.STRING_VALUE_SUBSTRACTION)) {
                        newS = Calculators.ZERO_VALUE;
                    } else {
                        newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);
                    }
                } else if (e.keyCode != SWT.BS && e.keyCode != Calculators.CHAR_VALUE_SUBSTRACTION) {
                    newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);
                }
                if (newS.matches("(([a-z]+\\d+)|(\\d+[a-z]+))[a-z\\d]*")) {
                    e.doit = false;
                }

                try {
                    Float.parseFloat(newS);
                } catch (NumberFormatException ex) {
                    isFloat = false;
                }
                if (!isFloat) {
                    e.doit = false;
                }
            }
        };

        setLayout(new FillLayout(SWT.HORIZONTAL));

        Composite composite = new Composite(this, SWT.NONE);
        composite.setLayout(new GridLayout(3, false));

        firstNumber = new Text(composite, SWT.BORDER);
        firstNumber.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        firstNumber.setText(Calculators.ZERO_VALUE);

        mathSign = new Combo(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
        mathSign.add(MathSignEnum.DIVIDE.getValue());
        mathSign.add(MathSignEnum.ADD.getValue());
        mathSign.add(MathSignEnum.SUBTRACT.getValue());
        mathSign.add(MathSignEnum.MULTIPLY.getValue());
        mathSign.select(0);

        secondNumber = new Text(composite, SWT.BORDER);
        secondNumber.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        secondNumber.setText(Calculators.ZERO_VALUE);

        firstNumber.addVerifyListener(inputNuberVerifyListener);
        secondNumber.addVerifyListener(inputNuberVerifyListener);
    }

    public Text getFirstNumber() {
        return firstNumber;
    }

    public Combo getMathSign() {
        return mathSign;
    }

    public Text getSecondNumber() {
        return secondNumber;
    }
}