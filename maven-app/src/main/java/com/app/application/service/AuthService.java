package com.app.application.service;

import com.app.domain.validation.Validator;
import com.app.domain.validation.ValidatorFactory;

import java.util.Map;

public class AuthService {

    /** Unsecure data, must be replaced with a database in the future. */
    private static final Map<String, String> USERS =
            Map.of("admin@example.com", "suP3r-sTr0Ng-@-sEcrEt-pAs5W0rD");

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 254;

    private final Validator<String> emailValidator;

    public AuthService() {
        this.emailValidator = ValidatorFactory.email();
    }

    public boolean login(String email, String password) {
        if (!emailValidator.validate(email)) return false;
        if (!isValidPassword(password)) return false;

        String stored = USERS.get(email);
        if (stored == null) return false;

        return stored.equals(password);
    }

    private boolean isValidPassword(String password) {
        if (password == null || password.isBlank()) return false;
        return password.length() >= MIN_PASSWORD_LENGTH
                && password.length() <= MAX_PASSWORD_LENGTH;
    }
}
