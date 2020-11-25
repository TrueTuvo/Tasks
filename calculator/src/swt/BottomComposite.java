package swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class BottomComposite extends Composite {

    public static final String ZERO_VALUE = "0";

    private Text result;

    public BottomComposite(Composite parent, int style) {
        super(parent, style);
        setLayout(new FillLayout(SWT.HORIZONTAL));

        Composite composite = new Composite(this, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));

        Label label = new Label(composite, SWT.NONE);
        label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        label.setText("Result: ");

        result = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
        result.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        result.setText(ZERO_VALUE);
    }

    public Text getResult() {
        return result;
    }
}
