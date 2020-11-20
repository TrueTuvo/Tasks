package swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;

public class CalculatorSWT {

/**
 *  Initializes the calculator.
 */
    public void init() {

        Display display = new Display();
        Shell shell = new Shell(display);
        Rectangle screenSize = display.getPrimaryMonitor().getBounds();
        shell.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
        shell.setLayout(new GridLayout(1,false));


        new MainComposite(shell, SWT.NONE);
        shell.pack ();
        shell.setLocation((screenSize.width - shell.getBounds().width) / 2, (screenSize.height - shell.getBounds().height) / 2);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

    }

}
