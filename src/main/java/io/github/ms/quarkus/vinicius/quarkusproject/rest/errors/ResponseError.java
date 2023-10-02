package io.github.ms.quarkus.vinicius.quarkusproject.rest.errors;

import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {

    private String message;

    private Collection<FieldErrorMessage> fieldErrorMessages;

    public static <T> ResponseError createFromValidation(Set<ConstraintViolation<T>> constraintViolations) {
        List<FieldErrorMessage> fieldErrorsWithStreamMap = constraintViolations.stream()
                .map(constraint -> new FieldErrorMessage(constraint.getPropertyPath().toString(),
                        constraint.getMessage()))
                .collect(Collectors.toList());

        String message = "Validation Error";
        return new ResponseError(message, fieldErrorsWithStreamMap);
    }
}
