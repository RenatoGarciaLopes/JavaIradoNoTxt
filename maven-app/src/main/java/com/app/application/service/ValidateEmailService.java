package com.app.application.service;

import com.app.domain.validation.Validator;
import com.app.domain.validation.ValidatorFactory;

public class ValidateEmailService {

    private final Validator<String> validator;

    public ValidateEmailService() {
        this.validator = ValidatorFactory.email();
    }

    public boolean validate(String email) {
        return validator.validate(email);
    }
}
