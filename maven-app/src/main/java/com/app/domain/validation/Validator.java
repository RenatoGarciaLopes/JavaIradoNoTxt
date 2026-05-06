package com.app.domain.validation;

public interface Validator<T> {
    boolean validate(T input);
}
