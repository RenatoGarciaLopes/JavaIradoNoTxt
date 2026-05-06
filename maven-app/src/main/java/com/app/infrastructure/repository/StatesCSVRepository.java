package com.app.infrastructure.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StatesCSVRepository {

    private static final String SEPARATOR = ";";
    private static final int EXPECTED_COLUMNS = 3;

    private final Path csvPath;

    public StatesCSVRepository(Path csvPath) {
        this.csvPath = csvPath;
    }

    public List<String[]> findAll() throws IOException {
        List<String[]> states = new ArrayList<>();
        boolean firstLine = true;

        try (BufferedReader reader = Files.newBufferedReader(csvPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                if (line.isBlank()) continue;

                String[] columns = line.split(SEPARATOR);
                if (columns.length < EXPECTED_COLUMNS) continue;

                states.add(new String[]{
                    columns[0].trim(),
                    columns[1].trim(),
                    columns[2].trim()
                });
            }
        }

        return states;
    }
}
