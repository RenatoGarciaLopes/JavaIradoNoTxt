package ValidateCNPJ;

public class TestValidateCNPJ {

  public static void main(String[] args) {
    String[] testCnpjs = {
        "11.222.333/0001-81", // Valid
        "00.000.000/0000-00", // Invalid (identical digits)
        "12345678000195", // Valid
        "11111111111111",     // Invalid (identical digits)
        "12345678901234",     // Invalid (wrong verification digits)
        "abc",                // Invalid (non-numeric)
        null                  // Invalid (null)
    };

    for (String cnpj : testCnpjs) {
      boolean isValid = ValidateCNPJ.validate(cnpj);
      System.out.printf("CNPJ: %s - Valid: %b%n", cnpj, isValid);
    }
  }

}
