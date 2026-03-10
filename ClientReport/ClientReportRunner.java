package ClientReport;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe de exemplo que utiliza {@link ClientReport} para ler um arquivo
 * de clientes e imprimir o relatório na tela.
 */
public class ClientReportRunner {

  public static void main(String[] args) {
    Path exemplo = Paths.get("ClientReport", "exemplo-clientes.txt");

    ClientReport report = new ClientReport();
    report.setFilePath(exemplo.toString());

    try {
      report.printReport();
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }
  }
}
