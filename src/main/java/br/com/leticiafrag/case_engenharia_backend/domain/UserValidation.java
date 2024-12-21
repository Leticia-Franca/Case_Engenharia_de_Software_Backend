package br.com.leticiafrag.case_engenharia_backend.domain;

/*
* Enum representing the validation results for a user.
* Each value in this enum corresponds to a specific validation rule
* applied to the user data (such as name, email, and age). The enum
* includes an associated error code and message to describe the result of
* each validation check.
*
* This way, the returns in the isUserValid method (implemented in UserService)
* are more organized and clear.
* */
public enum UserValidation {
    VALID(0, "User is valid."),
    NAME_EMPTY(1, "ERROR: The name cannot be empty."),
    NAME_LENGTH(2, "ERROR: The name must have between 3 and 50 characters."),
    NAME_INVALID(3, "ERROR: The name must have only letters and spaces."),
    EMAIL_EMPTY(4, "ERROR: The email cannot be empty."),
    EMAIL_INVALID(5, "ERROR: The email is invalid."),
    EMAIL_DUPLICATED(6, "ERROR: The email is already being used."),
    AGE_INVALID(7, "ERROR: The age must be between 0 and 123.");

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

