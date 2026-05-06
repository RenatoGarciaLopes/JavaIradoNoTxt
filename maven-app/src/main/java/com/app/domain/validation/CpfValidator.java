package com.app.domain.validation;

public class CpfValidator implements Validator<String> {

    @Override
    public boolean validate(String cpf) {
        if (cpf == null || cpf.isBlank())
            return false;

        String digits = cpf.replaceAll("\\D", "");

        if (!digits.matches("\\d{11}"))
            return false;

        if (digits.chars().distinct().count() == 1)
            return false;

        int firstDigit = calculateDigit(digits, 9, 10);
        int secondDigit = calculateDigit(digits, 10, 11);

        return firstDigit == Character.getNumericValue(digits.charAt(9))
                && secondDigit == Character.getNumericValue(digits.charAt(10));
    }

    private int calculateDigit(String digits, int length, int weightStart) {
        int sum = 0;
        int weight = weightStart;

        for (int i = 0; i < length; i++) {
            int num = Character.getNumericValue(digits.charAt(i));
            sum += num * weight;
            weight--;
        }

        int remainder = (sum * 10) % 11;
        return remainder == 10 ? 0 : remainder;
    }
}
