package ClientReport;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Example class that uses {@link ClientReport} to read a client file
 * and print the report to the console.
 */
public class ClientReportRunner {

  public static void main(String[] args) {
    Path samplePath = Paths.get("ClientReport", "clients_test.txt");

    ClientReport report = new ClientReport();
    report.setFilePath(samplePath.toString());

    try {
      report.printReport();
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
  }
}
