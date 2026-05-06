package com.app.application.service;

import com.app.domain.model.Client;
import com.app.domain.validation.Validator;
import com.app.domain.validation.ValidatorFactory;
import com.app.infrastructure.repository.ClientRepository;

import java.io.IOException;

public class ClientService {

    private final ClientRepository repository;
    private final Validator<String> cpfValidator;
    private final Validator<String> emailValidator;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
        this.cpfValidator = ValidatorFactory.cpf();
        this.emailValidator = ValidatorFactory.email();
    }

    public void register(String name, String cpf, String email) throws IOException {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("nome obrigatório.");

        if (!cpfValidator.validate(cpf))
            throw new IllegalArgumentException("cpf inválido.");

        if (!emailValidator.validate(email))
            throw new IllegalArgumentException("e-mail inválido.");

        repository.save(new Client(name.trim(), cpf.trim(), email.trim()));
    }
}
