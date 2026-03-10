package StatesCSVReader;

import java.io.IOException;
import java.nio.file.Path;

public class TestStatesCSVReader {
  public static void main(String[] args) {
    Path[] testPaths = {
        Path.of("StatesCSVReader/estados.csv"), // Valid path
        Path.of("invalid/path.csv"),            // Invalid path
        null                                    // Null path
    };

    for (Path path : testPaths) {
      try {
        StatesCSVReader reader = new StatesCSVReader(path);
        reader.display();
        System.out.println("Leitura bem-sucedida para: " + path);
      } catch (IOException e) {
        System.out.println("Erro ao ler o arquivo para: " + path + " - " + e.getMessage());
      } catch (Exception e) {
        System.out.println("Erro geral para: " + path + " - " + e.getMessage());
      }
      System.out.println();
    }
  }
}