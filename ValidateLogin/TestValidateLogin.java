package ValidateLogin;

public class TestValidateLogin {
  public static void main(String[] args) {
    ValidateLogin validateLogin = new ValidateLogin("admin1@example.com", "suP3r-sTr0Ng-@-sEcrEt-pAs5W0rD");
    System.out.println(validateLogin.isValid() ? "Valid login" : "Invalid login");
  }
}
