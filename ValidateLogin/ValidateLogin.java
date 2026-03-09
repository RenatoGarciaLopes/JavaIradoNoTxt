package ValidateLogin;

import java.util.Map;
import ValidateEmail.ValidateEmail;

public class ValidateLogin {
  /**
   * Users and their passwords. Unsecure data, must be replaced with a database in the future.
   */
  private Map<String, String> USERS = Map.of("admin@example.com", "suP3r-sTr0Ng-@-sEcrEt-pAs5W0rD");

  private static final int MIN_PASSWORD_LENGTH = 8;
  private static final int MAX_PASSWORD_LENGTH = 254;

  private String email;
  private String password;

  private ValidateLogin(String email, String password) {
    this.email = email;
    this.password = password;
  }

  /**
   * Validates login with email and password (no hash).
   * @param email user email
   * @param password plain text password
   * @return true if valid
   */
  public boolean isValid() {
    if (!hasValidEmail(email) || !hasValidPassword(password)) return false;

    String userPassword = USERS.get(email);
    if (userPassword == null) return false;

    return userPassword.equals(password);
  }

  private boolean hasValidEmail(String email) {
    return ValidateEmail.validate(email);
  }

  private boolean hasValidPassword(String password) {
    if (password == null || password.isBlank())
      return false;

    if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH)
      return false;

    return true;
  }
}
