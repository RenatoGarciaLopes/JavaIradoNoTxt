package StatesCSVReader;

import java.io.IOException;
import java.nio.file.Path;

public class TestStatesCSVReader {
  public static void main(String[] args) {
    Path[] testPaths = {
        Path.of("StatesCSVReader/states.csv"), // Valid path
        Path.of("invalid/path.csv"),            // Invalid path
        null                                    // Null path
    };

    for (Path path : testPaths) {
      try {
        StatesCSVReader reader = new StatesCSVReader(path);
        reader.display();
        System.out.println("Read succeeded for: " + path);
      } catch (IOException e) {
        System.out.println("Error reading file for: " + path + " - " + e.getMessage());
      } catch (Exception e) {
        System.out.println("General error for: " + path + " - " + e.getMessage());
      }
      System.out.println();
    }
  }
}