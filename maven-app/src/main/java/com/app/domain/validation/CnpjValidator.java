package com.app.domain.validation;

public class CnpjValidator implements Validator<String> {

    private static final int[] FIRST_WEIGHTS = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
    private static final int[] SECOND_WEIGHTS = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

    @Override
    public boolean validate(String cnpj) {
        if (cnpj == null || cnpj.isBlank())
            return false;

        String digits = cnpj.replaceAll("\\D", "");

        if (!digits.matches("\\d{14}"))
            return false;

        if (digits.chars().distinct().count() == 1)
            return false;

        int firstDigit = calculateDigit(digits, FIRST_WEIGHTS);
        int secondDigit = calculateDigit(digits, SECOND_WEIGHTS);

        return firstDigit == Character.getNumericValue(digits.charAt(12))
                && secondDigit == Character.getNumericValue(digits.charAt(13));
    }

    private int calculateDigit(String digits, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += Character.getNumericValue(digits.charAt(i)) * weights[i];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}
