package StatesCSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Thread.State;
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
  public List<State> read() throws IOException {

    List<State> states = new ArrayList<>();

    try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
      reader.readLine();

      String line;
      while ((line = reader.readLine()) != null) {
        if (line.isBlank())
          continue;

        String[] columns = line.split(SEPARATOR, -1);

        if (columns.length != EXPECTED_COLUMNS)
          continue;

        states.add(new State(
            Integer.parseInt(columns[0].trim()),
            columns[1].trim(),
            columns[2].trim()
        ));
      }
    }

    return states;
  }

  /**
   * Reads the CSV and prints each state's information.
   */
  public void display() throws IOException {

    List<State> states = read();

    System.out.printf("%-12s %-8s %s%n", "State Code", "Abbr", "Name");
    // Java 8 compatibility: build a line of 45 dashes
    System.out.println(new String(new char[45]).replace('\0', '-'));

    for (State state : states) {
      System.out.printf("%-12d %-8s %s%n",
          state.stateCode(), state.abbreviation(), state.name());
    }
  }

  /**
   * Value object representing a Brazilian state.
   */
  public void State(int stateCode, String abbreviation, String name) { }

  public static void main(String[] args) throws IOException {
    Path path = Path.of("StatesCSVReader", "states.csv");
    new StatesCSVReader(path).display();
  }

}
