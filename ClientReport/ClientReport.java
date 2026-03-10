package ClientReport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lê um arquivo TXT com dados de clientes e imprime um relatório na tela.
 * <p>
 * Aceita dois formatos de entrada:
 * <ul>
 *   <li>Simples: CPF | e-mail | nome</li>
 *   <li>Com timestamp: [dd-mm-yyyyThh:mm:ss] | CPF | e-mail | nome</li>
 * </ul>
 * <p>
 * Formato de saída: [dd-mm-yyyyThh:mm:ss] CPF | e-mail | nome
 */
public class ClientReport {

  /** Formato do horário no log */
  private static final DateTimeFormatter LOG_TIME =
      DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss");

  /** SEPARADOR de campos no TXT e no log (espaço, pipe, espaço) */
  private static final String SEPARATOR = " \\| ";

  private String filePath;

  public void setFilePath(String path) {
    this.filePath = path;
  }

  /**
   * Lê o arquivo configurado em filePath e imprime cada cliente no formato de log.
   */
  public void printReport() throws IOException {
    var linhas = Files.readAllLines(Paths.get(filePath));

    for (String line : linhas) {
      String trimmedLine = line.trim();
      if (trimmedLine.isEmpty()) {
        continue;
      }

      String[] fields = trimmedLine.split(SEPARATOR);

      String cpf;
      String email;
      String nome;

      // Formato com timestamp: [dd-mm-yyyyThh:mm:ss] | CPF | e-mail | nome
      if (fields.length >= 4 && fields[0].trim().startsWith("[")) {
        String timestamp = fields[0].trim();
        cpf = fields[1].trim();
        email = fields[2].trim();
        nome = fields[3].trim();

        System.out.printf("%s | %s | %s | %s%n", timestamp, cpf, email, nome);

      }
      // Formato simples: CPF | e-mail | nome
      else
      {
        cpf = fields[0].trim();
        email = fields[1].trim();
        nome = fields[2].trim();

        String timestamp = LocalDateTime.now().format(LOG_TIME);
        System.out.printf("[%s] | %s | %s | %s%n", timestamp, cpf, email, nome);
      }
    }
  }
}
