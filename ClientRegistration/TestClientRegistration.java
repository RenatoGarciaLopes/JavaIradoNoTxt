package ClientRegistration;

import java.io.IOException;

/**
 * Test class to validate saving clients to a TXT file.
 * Follows the same implementation pattern used in the project's
 * validation and reading test classes.
 */
public class TestClientRegistration {

  public static void main(String[] args) {
    // Instantiate the registration class as defined in ClientRegistration.java
    ClientRegistration registration = new ClientRegistration();
    
    // Configure the output file path
    registration.setFilePath("clients_test.txt");

    // Test data matrix (name, cpf, email) following the array pattern used in TestValidateCNPJ.java
    String[][] testData = {
        {"Ana Silva", "123.456.789-00", "ana.silva@email.com"},
        {"Carlos Souza", "987.654.321-11", "carlos@provedor.com.br"},
        {"Beatriz Oliveira", "111.222.333-44", "beatriz.o@empresa.com"}
    };

    System.out.println("Starting client save tests...");

    for (String[] client : testData) {
      try {
        // Call the persistence method
        registration.save(client[0], client[1], client[2]);
        System.out.printf("Success: Client '%s' saved.%n", client[0]);
      } catch (IOException e) {
        // Input/output error handling following the pattern of TestStatesCSVReader.java
        System.out.println("Error writing file for: " + client[0] + " - " + e.getMessage());
      } catch (Exception e) {
        // Generic exception handling according to the project pattern
        System.out.println("General error for: " + client[0] + " - " + e.getMessage());
      }
    }

    System.out.println("\nTest finished. Check the contents of 'clients_test.txt'.");
  }
}