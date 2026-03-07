package ValidateCNPJ;

public class ValidateCNPJ {

  private ValidateCNPJ() { }

  /**
   * Weights used to calculate the first verification digit.
   */
  private static final int[] FIRST_WEIGHTS = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

  /**
   * Weights used to calculate the second verification digit.
   */
  private static final int[] SECOND_WEIGHTS = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

  /**
   * Validates a CNPJ number.
   *
   * <p>
   * The input may contain formatting characters such as dots,
   * slashes or dashes (e.g. {@code 12.345.678/0001-95}).
   * These characters will be ignored during validation.
   * </p>
   *
   * @param cnpj the CNPJ string to be validated
   * @return {@code true} if the CNPJ is valid, {@code false} otherwise
   */
  public static boolean validate(String cnpj) {

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

  /**
   * Calculates a CNPJ verification digit.
   *
   * <p>
   * The algorithm multiplies each digit by a predefined weight,
   * sums the results and applies a modulus 11 operation to
   * determine the verification digit.
   * </p>
   *
   * @param digits the numeric CNPJ string
   * @param weights the weights used in the calculation
   * @return the calculated verification digit
   */
  private static int calculateDigit(String digits, int[] weights) {

    int sum = 0;

    for (int i = 0; i < weights.length; i++) {
      sum += Character.getNumericValue(digits.charAt(i)) * weights[i];
    }

    int remainder = sum % 11;

    return remainder < 2 ? 0 : 11 - remainder;
  }

}
