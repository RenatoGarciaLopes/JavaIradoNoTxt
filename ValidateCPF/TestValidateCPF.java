package ValidateCPF;

public class TestValidateCPF {
  public static void main(String[] args) {
    String[] testCpfs = {
        "123.456.789-09", // Valid
        "000.000.000-00", // Invalid (identical digits)
        "70306700000",    // Valid
        "11111111111",    // Invalid (identical digits)
        "12345678901",    // Invalid (wrong verification digits)
        "abc",            // Invalid (non-numeric)
        null              // Invalid (null)
    };

    for (String cpf : testCpfs) {
      boolean isValid = ValidateCPF.validate(cpf);
      System.out.printf("CPF: %s - Válido: %b%n", cpf, isValid);
    }
  }

}
