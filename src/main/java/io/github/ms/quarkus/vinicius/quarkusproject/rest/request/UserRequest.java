package io.github.ms.quarkus.vinicius.quarkusproject.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;

    @NotBlank(message = "Name must not be blank!")
    private String name;
    private String emailAddress;

    @NotNull(message = "RG must not be null")
    private Long rg;

    @NotNull(message = "CPF must not be null")
    private String cpf;
}
