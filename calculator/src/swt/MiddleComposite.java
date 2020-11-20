package swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class MiddleComposite extends Composite {

    private final String nameCheckedButton = "Calculate on the fly";
    private final String namePressedButton = "Calculate";

    private Button isCOTF;
    private Button calculateButton;

    public MiddleComposite(Composite parent, int style) {
        super(parent, style);
        setLayout(new FillLayout(SWT.HORIZONTAL));

        Composite composite = new Composite(this, SWT.NONE);
        composite.setLayout(new FillLayout(SWT.HORIZONTAL));

        isCOTF = new Button(composite, SWT.CHECK);
        isCOTF.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        isCOTF.setText(nameCheckedButton);

        calculateButton = new Button(composite, SWT.PUSH);
        calculateButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        calculateButton.setText(namePressedButton);
    }

    public Button getIsCOTF() {
        return isCOTF;
    }

    public Button getCalculateButton() {
        return calculateButton;
    }
}
