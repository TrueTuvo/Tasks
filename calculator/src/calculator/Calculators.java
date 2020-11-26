package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculators {
    public static final String EMPTY_STRING = "";
    public static final String STRING_VALUE_DOT = ".";
    public static final String STRING_VALUE_SUBSTRACTION = "-";
    public static final char CHAR_VALUE_SUBSTRACTION = '-';
    public static final String OPERATION_DIVISION = "/";
    
    public static final String ZERO_VALUE = "0";
    public static final String DOUBLE_ZERO_VALUE = "0.0";
    
    public static final String NAME_CHECKED_BUTTON = "Calculate on the fly";
    public static final String NAME_PRESSED_BUTTON = "Calculate";
    public static final String MATH_EXCEPTION_MESSAGE = "Arithmetic Error";




    /**
     *  collects the passed parameters into a mathematical expression and determines its result
     *  
     * @param firstNumber - first number for calculate
     * @param secondNumber - second number for calculate
     * @param mathSign - Math Sign what will be use for Math expression
     * @return - result of math expression
     */
    public static BigDecimal calculate(BigDecimal firstNumber, BigDecimal secondNumber, MathSignEnum mathSign) {

        switch (mathSign) {

        case DIVIDE:
            return firstNumber.divide(secondNumber, 5, RoundingMode.HALF_UP);

        case ADD:

            return firstNumber.add(secondNumber);

        case SUBTRACT:

            return firstNumber.subtract(secondNumber);

        case MULTIPLY:

            return firstNumber.multiply(secondNumber);

        default:
            break;
        }
        return new BigDecimal(0);
    }

    /**
     * Check both fields for emptiness in case of division, the second field is checked for 0
     * 
     * @param first first number
     * @param second second number
     * @param mathSign mathSign number
     * @return true if inputs valid, false otherwise
     */
    public static boolean isValidate(String first, String second, MathSignEnum mathSign) {
        if (mathSign.getValue().equals(OPERATION_DIVISION) && (second.equals(DOUBLE_ZERO_VALUE) || second.equals(ZERO_VALUE))) {
            return false;
        }
        if (first.equals(EMPTY_STRING) || second.equals(EMPTY_STRING)) {
            return false;
        }
        return true;
    }

    /**
     * Collects the passed parameters into a mathematical expression as a string
     * 
     * @param firstNumber firstNumber
     * @param secondNumber secondNumber
     * @param mathSign  mathSign
     * @param finalResult finalResult
     * @return  returns String of math expression
     */
    public static String createCalculatedLine(BigDecimal firstNumber, BigDecimal secondNumber, MathSignEnum mathSign,
            BigDecimal finalResult) {

        return String.format("%s %s %s = %s", firstNumber.toString(), mathSign.getValue(), secondNumber.toString(),
                finalResult.toString());
    }
}
