package ValidateEmail;

public class TestValidateEmail {
  public static void main(String[] args) {
    String[] testEmails = {
        "user@example.com",          // Valid
        "user.name@domain.com.br",   // Valid
        "user+tag@sub.domain.org",   // Valid
        "a@bc.de",                   // Valid (minimal)
        "user@domain",               // Invalid (no TLD)
        "user@@domain.com",          // Invalid (double @)
        "@domain.com",               // Invalid (empty local part)
        "user@.com",                 // Invalid (domain starts with dot)
        "user@domain.",              // Invalid (empty TLD)
        ".user@domain.com",          // Invalid (local starts with dot)
        "user.@domain.com",          // Invalid (local ends with dot)
        "us..er@domain.com",         // Invalid (consecutive dots in local)
        "user@domain.c",             // Invalid (TLD too short)
        "user@-domain.com",          // Invalid (label starts with hyphen)
        "",                          // Invalid (empty)
        null                         // Invalid (null)
    };

    for (String email : testEmails) {
      boolean isValid = ValidateEmail.validate(email);
      System.out.printf("Email: %-30s - Valid: %b%n", email, isValid);
    }
  }
}
