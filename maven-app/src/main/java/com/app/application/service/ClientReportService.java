package com.app.application.service;

import com.app.domain.model.Client;
import com.app.infrastructure.repository.ClientRepository;

import java.io.IOException;
import java.util.List;

public class ClientReportService {

    private final ClientRepository repository;

    public ClientReportService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> getReport() throws IOException {
        return repository.findAll();
    }
}
