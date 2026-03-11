package ClientReport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Reads a TXT file containing client data and prints a report to the console.
 * <p>
 * Accepts two input formats:
 * <ul>
 *   <li>Simple: CPF | email | name</li>
 *   <li>With timestamp: [dd-mm-yyyyThh:mm:ss] | CPF | email | name</li>
 * </ul>
 * <p>
 * Output format: [dd-mm-yyyyThh:mm:ss] CPF | email | name
 */
public class ClientReport {

  /** Timestamp format used in logs. */
  private static final DateTimeFormatter LOG_TIME =
      DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss");

  /** Field separator in input TXT and output logs (space, pipe, space). */
  private static final String SEPARATOR = " \\| ";

  private String filePath;

  public void setFilePath(String path) {
    this.filePath = path;
  }

  /**
   * Reads the configured filePath and prints each client in log format.
   */
  public void printReport() throws IOException {
    var lines = Files.readAllLines(Paths.get(filePath));

    for (String line : lines) {
      String trimmedLine = line.trim();
      if (trimmedLine.isEmpty()) {
        continue;
      }

      String[] fields = trimmedLine.split(SEPARATOR);

      String cpf;
      String email;
      String name;

      // Format with timestamp: [dd-mm-yyyyThh:mm:ss] | CPF | email | name
      if (fields.length >= 4 && fields[0].trim().startsWith("[")) {
        String timestamp = fields[0].trim();
        cpf = fields[1].trim();
        email = fields[2].trim();
        name = fields[3].trim();

        System.out.printf("%s | %s | %s | %s%n", timestamp, cpf, email, name);

      }
      // Simple format: CPF | email | name
      else
      {
        cpf = fields[0].trim();
        email = fields[1].trim();
        name = fields[2].trim();

        String timestamp = LocalDateTime.now().format(LOG_TIME);
        System.out.printf("[%s] | %s | %s | %s%n", timestamp, cpf, email, name);
      }
    }
  }
}
