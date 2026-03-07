package ValidateCPF;

public class ValidateCPF {
  
  private ValidateCPF() { }

  /**
   * Validates a CPF number.
   *
   * <p>
   * The input may contain formatting characters such as dots or dashes
   * (e.g. {@code 123.456.789-09}). These characters will be ignored during
   * validation.
   * </p>
   *
   * @param cpf the CPF string to be validated
   * @return {@code true} if the CPF is valid, {@code false} otherwise
   */
  public static boolean validate(String cpf) {

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

  /**
   * Calculates a CPF verification digit.
   *
   * <p>
   * The calculation multiplies each digit by a descending weight and
   * applies a modulus 11 operation to determine the verification digit.
   * </p>
   *
   * @param digits the numeric CPF string
   * @param length the number of digits to consider in the calculation
   * @param weightStart the starting weight used in the multiplication
   * @return the calculated verification digit
   */
  private static int calculateDigit(String digits, int length, int weightStart) {

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
