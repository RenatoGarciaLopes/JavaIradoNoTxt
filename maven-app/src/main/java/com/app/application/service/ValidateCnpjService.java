package com.app.application.service;

import com.app.domain.validation.Validator;
import com.app.domain.validation.ValidatorFactory;

public class ValidateCnpjService {

    private final Validator<String> validator;

    public ValidateCnpjService() {
        this.validator = ValidatorFactory.cnpj();
    }

    public boolean validate(String cnpj) {
        return validator.validate(cnpj);
    }
}
