package io.github.ms.quarkus.vinicius.quarkusproject.rest.errors;

public class FieldErrorMessage {

    private String field;
    private String message;

    public FieldErrorMessage() { }

    public FieldErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
