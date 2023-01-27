package calculator.domain;

import calculator.utils.CalculatorUtils;

public class Calculator {
    private static final int FIRST_INDEX = 0;
    private static final int CALCULATION_UNIT_VALUE = 2;
    private static final String INPUT_VALUE_EMPTY = "입력 값이 비어 있습니다.";

    public int getResult(String input) {
        validate(input);
        return calculate(CalculatorUtils.split(input));
    }

    private void validate(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(INPUT_VALUE_EMPTY);
        }
    }

    private int calculate(String[] target) {
        int length = target.length;
        int initialValue = getFirstValue(target);

        return accumulateResult(length, initialValue, target);
    }

    private int accumulateResult(int length, int initialValue, String[] target) {
        int totalValue = initialValue;

        for (int index = 2; index < length; index += CALCULATION_UNIT_VALUE) {
            totalValue = Operator.operate(totalValue, getOperationSymbol(index, target), getSecondValue(index, target));
        }

        return totalValue;
    }

    private int getFirstValue(String[] target) {
        return CalculatorUtils.toInt(target[FIRST_INDEX]);
    }

    private int getSecondValue(int index, String[] target) {
        return CalculatorUtils.toInt(target[index]);
    }

    private String getOperationSymbol(int index, String[] target) {
        return target[index - 1];
    }

}
