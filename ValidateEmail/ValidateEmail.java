package ValidateEmail;

/**
 * Utility class responsible for validating email addresses.
 *
 */
public class ValidateEmail {

  private static final String LOCAL_PART_PATTERN = "^[a-zA-Z0-9._%+-]+$";
  private static final String DOMAIN_CHAR_PATTERN = "^[a-zA-Z0-9.-]+$";
  private static final String DOMAIN_LABEL_PATTERN = "^[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?$";
  private static final int MAX_LOCAL_LENGTH = 64;
  private static final int MAX_DOMAIN_LENGTH = 255;
  private static final int MIN_TLD_LENGTH = 2;

  private ValidateEmail() { }

  /**
   * Validates an email address format.
   */
  public static boolean validate(String email) {

    if (email == null || email.isBlank())
      return false;

    String[] parts = email.split("@", -1);

    if (parts.length != 2)
      return false;

    return isValidLocalPart(parts[0]) && isValidDomain(parts[1]);
  }

  /**
   * Validates the local part of an email (before {@code @}).
   */
  private static boolean isValidLocalPart(String localPart) {

    if (localPart.isEmpty() || localPart.length() > MAX_LOCAL_LENGTH)
      return false;

    if (localPart.startsWith(".") || localPart.endsWith("."))
      return false;

    if (localPart.contains(".."))
      return false;

    return localPart.matches(LOCAL_PART_PATTERN);
  }

  /**
   * Validates the domain part of an email (after {@code @}).
   */
  private static boolean isValidDomain(String domain) {

    if (domain.isEmpty() || domain.length() > MAX_DOMAIN_LENGTH)
      return false;

    if (!domain.matches(DOMAIN_CHAR_PATTERN))
      return false;

    String[] labels = domain.split("\\.", -1);

    if (labels.length < 2)
      return false;

    for (String label : labels) {
      if (!label.matches(DOMAIN_LABEL_PATTERN))
        return false;
    }

    String tld = labels[labels.length - 1];

    return tld.length() >= MIN_TLD_LENGTH && tld.matches("^[a-zA-Z]+$");
  }

}
