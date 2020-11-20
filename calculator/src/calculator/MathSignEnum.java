package calculator;

public enum MathSignEnum {
    DIVIDE("/"), ADD("+"), SUBTRACT("-"), MULTIPLY("*");

    private final String value;

    private MathSignEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
