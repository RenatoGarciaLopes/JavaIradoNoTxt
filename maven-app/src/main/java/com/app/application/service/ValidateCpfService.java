package com.app.application.service;

import com.app.domain.validation.Validator;
import com.app.domain.validation.ValidatorFactory;

public class ValidateCpfService {

    private final Validator<String> validator;

    public ValidateCpfService() {
        this.validator = ValidatorFactory.cpf();
    }

    public boolean validate(String cpf) {
        return validator.validate(cpf);
    }
}
