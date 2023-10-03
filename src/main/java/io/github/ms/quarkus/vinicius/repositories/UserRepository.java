package io.github.ms.quarkus.vinicius.repositories;

import io.github.ms.quarkus.vinicius.quarkusproject.rest.request.UserRequest;
import jakarta.ws.rs.core.Response;


public interface UserRepository {

    Response createUser(UserRequest userRequest);
    Response listAllUsers();
    Response updateUser(Long id, UserRequest userRequest);
    Response deleteUser(Long id);
}
