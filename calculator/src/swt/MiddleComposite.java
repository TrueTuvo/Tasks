package swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import calculator.Calculators;

public class MiddleComposite extends Composite {

    private final Button isCOTF;
    private final Button calculateButton;

    public MiddleComposite(Composite parent, int style) {
        super(parent, style);
        setLayout(new FillLayout(SWT.HORIZONTAL));

        Composite composite = new Composite(this, SWT.NONE);
        composite.setLayout(new FillLayout(SWT.HORIZONTAL));

        isCOTF = new Button(composite, SWT.CHECK);
        isCOTF.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        isCOTF.setText(Calculators.NAME_CHECKED_BUTTON);

        calculateButton = new Button(composite, SWT.PUSH);
        calculateButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        calculateButton.setText(Calculators.NAME_PRESSED_BUTTON);
    }

    public Button getIsCOTF() {
        return isCOTF;
    }

    public Button getCalculateButton() {
        return calculateButton;
    }
}
