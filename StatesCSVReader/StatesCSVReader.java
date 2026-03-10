package StatesCSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a CSV file containing Brazilian states and displays
 */
public class StatesCSVReader {

  private static final String SEPARATOR = ";";
  private static final int EXPECTED_COLUMNS = 3;

  private final Path csvPath;

  /**
   * @param csvPath path to the CSV file with state data
   */
  public StatesCSVReader(Path csvPath) {
    this.csvPath = csvPath;
  }

  /**
   * Parses the CSV file and returns a list of states.
   */
  public List<Estado> read() throws IOException {

    List<Estado> estados = new ArrayList<>();

    try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
      reader.readLine();

      String line;
      while ((line = reader.readLine()) != null) {
        if (line.isBlank())
          continue;

        String[] columns = line.split(SEPARATOR, -1);

        if (columns.length != EXPECTED_COLUMNS)
          continue;

        estados.add(new Estado(
            Integer.parseInt(columns[0].trim()),
            columns[1].trim(),
            columns[2].trim()
        ));
      }
    }

    return estados;
  }

  /**
   * Reads the CSV and prints each state's information to
   */
  public void display() throws IOException {

    List<Estado> estados = read();

    System.out.printf("%-12s %-8s %s%n", "Código UF", "Sigla", "Nome");
    System.out.println("-".repeat(45));

    for (Estado estado : estados) {
      System.out.printf("%-12d %-8s %s%n",
          estado.codigoUF(), estado.sigla(), estado.nome());
    }
  }

  /**
   * Value object representing a Brazilian state.
   */
  public record Estado(int codigoUF, String sigla, String nome) { }

  public static void main(String[] args) throws IOException {
    Path path = Path.of("LeituraCSVEstados", "estados.csv");
    new StatesCSVReader(path).display();
  }

}
