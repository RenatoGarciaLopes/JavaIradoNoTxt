package ClientReport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class responsible for reading TXT files containing client data
 * and printing each valid record as a log line.
 *
 * <p>
 * This class expects each non-blank line to start with a timestamp in
 * square brackets followed by the client information, for example:
 * {@code [dd-MM-yyyy'T'HH:mm:ss] CPF | email | name}.
 * Lines that do not follow this pattern or that do not contain at least
 * CPF, email and name are ignored.
 * </p>
 */
public class ClientReport {


  /** Field separator in input TXT and output logs (space, pipe, space). */
  private static final String SEPARATOR = " \\| ";

  private String filePath;

  public void setFilePath(String path) {
    this.filePath = path;
  }

  /**
   * Reads the configured {@code filePath}, extracts client information from
   * each non-blank line that starts with a timestamp in square brackets and
   * prints it as a log entry.
   *
   * <p>
   * Lines that do not contain a closing bracket for the timestamp or do not
   * provide at least CPF, email and name after the timestamp are ignored.
   * </p>
   *
   * @throws IOException if an I/O error occurs while reading the file
   */
  public void printReport() throws IOException {
    var lines = Files.readAllLines(Paths.get(filePath));

    for (String line : lines) {
      String trimmedLine = line.trim();
      if (trimmedLine.isEmpty()) {
        continue;
      }
      
      String timestamp;
      String cpf;
      String email;
      String name;

      
      int endIdx = trimmedLine.indexOf(']');
      if (endIdx < 0) {
        continue;
      }

      timestamp = trimmedLine.substring(0, endIdx + 1).trim();
      String rest = trimmedLine.substring(endIdx + 1).trim();


      String[] fields = rest.split(SEPARATOR);
      if (fields.length < 3) {
        continue;
      }

      cpf = fields[0].trim();
      email = fields[1].trim();
      name = fields[2].trim();

      

      System.out.printf("%s %s | %s | %s%n", timestamp, cpf, email, name);
    }
  }
}
