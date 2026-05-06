package com.app.domain.validation;

public final class ValidatorFactory {

    private ValidatorFactory() { }

    public static Validator<String> cpf() {
        return new CpfValidator();
    }

    public static Validator<String> cnpj() {
        return new CnpjValidator();
    }

    public static Validator<String> email() {
        return new EmailValidator();
    }
}
