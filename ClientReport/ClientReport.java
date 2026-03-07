package ClientReport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lê um arquivo TXT com dados de clientes e imprime um relatório na tela.
 * <p>
 * Formato esperado do TXT: uma linha por cliente, campos separados por " | ":
 * CPF | e-mail | nome
 * <p>
 * Formato de cada linha do relatório: [dd-mm-yyyyThh:mm:ss] CPF | e-mail | nome
 */
public class ClientReport {

  /** Formato do horário no log: dia-mês-anoThora:minuto:segundo */
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
      if (trimmedLine.isEmpty()) 
      {
        continue;
      }

      // Quebra a linha nos campos: CPF | e-mail | nome
      String[] fields = trimmedLine.split(SEPARATOR);

      if (fields.length < 3) 
      {
        continue;
      }

      String cpf = fields[0].trim();
      String email = fields[1].trim();
      String nome = fields[2].trim();

      String timestamp = LocalDateTime.now().format(LOG_TIME);
      System.out.printf("[%s] %s | %s | %s%n", timestamp, cpf, email, nome);
    }
  }
}
