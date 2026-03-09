package ClientRegistration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Client registration: name, CPF and email to TXT file (no encryption).
 * Single responsibility: persist client data to TXT.
 */
public class ClientRegistration {

    private static final String SEPARATOR = " | ";
    private String filePath;

    public void setFilePath(String path) {
        this.filePath = path;
    }

    /**
     * Saves client (name, CPF, email) to TXT file.
     */
    public void save(String name, String cpf, String email) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter);

        String line = "[" + dateTime + "] "
                + cpf + SEPARATOR
                + email + SEPARATOR
                + name + System.lineSeparator();

        Files.write(
                Paths.get(filePath),
                line.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }
}