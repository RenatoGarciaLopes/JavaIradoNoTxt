package com.app.domain.validation;

public class EmailValidator implements Validator<String> {

    private static final String LOCAL_PART_PATTERN = "^[a-zA-Z0-9._%+-]+$";
    private static final String DOMAIN_CHAR_PATTERN = "^[a-zA-Z0-9.-]+$";
    private static final String DOMAIN_LABEL_PATTERN = "^[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?$";
    private static final int MIN_TLD_LENGTH = 2;

    @Override
    public boolean validate(String email) {
        if (email == null || email.isBlank())
            return false;

        String[] parts = email.split("@", -1);

        if (parts.length != 2)
            return false;

        return isValidLocalPart(parts[0]) && isValidDomain(parts[1]);
    }

    private boolean isValidLocalPart(String localPart) {
        if (localPart.isEmpty())
            return false;

        if (localPart.startsWith(".") || localPart.endsWith("."))
            return false;

        if (localPart.contains(".."))
            return false;

        return localPart.matches(LOCAL_PART_PATTERN);
    }

    private boolean isValidDomain(String domain) {
        if (domain.isEmpty())
            return false;

        if (!domain.matches(DOMAIN_CHAR_PATTERN))
            return false;

        String[] labels = domain.split("\\.", -1);

        if (labels.length < 2)
            return false;

        for (String label : labels) {
            if (!label.matches(DOMAIN_LABEL_PATTERN))
                return false;
        }

        String tld = labels[labels.length - 1];
        return tld.length() >= MIN_TLD_LENGTH && tld.matches("^[a-zA-Z]+$");
    }
}
