package ClientRegistration;

import java.io.IOException;

/**
 * Client registration: name, CPF and email to TXT file (no encryption).
 * Single responsibility: persist client data to TXT.
 */
public class ClientRegistration {

  public void setFilePath(String path) {
  }

  /**
   * Saves client (name, CPF, email) to TXT file.
   */
  public void save(String name, String cpf, String email) throws IOException {
  }
}
