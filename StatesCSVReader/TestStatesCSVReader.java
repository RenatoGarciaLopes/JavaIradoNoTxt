package StatesCSVReader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestStatesCSVReader {
  public static void main(String[] args) {

    Path[] testPaths = {
        Paths.get("StatesCSVReader/estados.csv"), // Caminho válido
        Paths.get("invalid/path.csv"),             // Caminho inválido
        null                                       // Caminho nulo
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