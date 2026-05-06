package com.app.application.service;

import com.app.infrastructure.repository.StatesCSVRepository;

import java.io.IOException;
import java.util.List;

public class StatesService {

    private final StatesCSVRepository repository;

    public StatesService(StatesCSVRepository repository) {
        this.repository = repository;
    }

    public List<String[]> getStates() throws IOException {
        return repository.findAll();
    }
}
