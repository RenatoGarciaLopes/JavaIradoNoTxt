package com.app.infrastructure.repository;

import com.app.domain.model.Client;

import java.io.IOException;
import java.util.List;

public interface ClientRepository {
    void save(Client client) throws IOException;
    List<Client> findAll() throws IOException;
}
