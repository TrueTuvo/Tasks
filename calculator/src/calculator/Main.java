package calculator;

import swing.CalculatorSwing;
import swt.CalculatorSWT;

public class Main {
    public static void main(String[] args) {
            CalculatorSWT calculatorSWT =  new CalculatorSWT();
            calculatorSWT.init();
            CalculatorSwing calculatorSwing = new CalculatorSwing();
            calculatorSwing.init();
	
    }
}
