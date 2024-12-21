package br.com.leticiafrag.case_engenharia_backend.domain;

public enum UserValidation {
    VALID(0, "User is valid."),
    NAME_EMPTY(1, "ERROR: The name cannot be empty."),
    NAME_LENGTH(2, "ERROR: The name must have between 3 and 50 characters."),
    NAME_INVALID(3, "ERROR: The name must have only letters and spaces."),
    EMAIL_EMPTY(4, "ERROR: The email cannot be empty."),
    EMAIL_INVALID(5, "ERROR: The informed email is invalid."),
    AGE_INVALID(6, "ERROR: Age must be between 0 and 123.");

    private final int code;
    private final String message;

    UserValidation(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

