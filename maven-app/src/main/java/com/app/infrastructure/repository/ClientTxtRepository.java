package com.app.infrastructure.repository;

import com.app.domain.model.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientTxtRepository implements ClientRepository {

    private static final String SEPARATOR = " | ";
    private static final String SEPARATOR_REGEX = " \\| ";
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss");

    private final Path filePath;

    public ClientTxtRepository(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(Client client) throws IOException {
        String dateTime = LocalDateTime.now().format(FORMATTER);
        String line = "[" + dateTime + "] "
                + client.cpf() + SEPARATOR
                + client.email() + SEPARATOR
                + client.name() + System.lineSeparator();

        Files.write(filePath, line.getBytes(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Override
    public List<Client> findAll() throws IOException {
        List<Client> clients = new ArrayList<>();

        if (!Files.exists(filePath))
            return clients;

        for (String line : Files.readAllLines(filePath)) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;

            int endIdx = trimmed.indexOf(']');
            if (endIdx < 0) continue;

            String rest = trimmed.substring(endIdx + 1).trim();
            String[] fields = rest.split(SEPARATOR_REGEX);
            if (fields.length < 3) continue;

            String cpf = fields[0].trim();
            String email = fields[1].trim();
            String name = fields[2].trim();
            clients.add(new Client(name, cpf, email));
        }

        return clients;
    }
}
