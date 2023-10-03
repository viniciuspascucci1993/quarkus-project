package io.github.ms.quarkus.vinicius.quarkusproject.rest;

import io.github.ms.quarkus.vinicius.model.UserModel;
import io.github.ms.quarkus.vinicius.quarkusproject.rest.request.UserRequest;
import io.github.ms.quarkus.vinicius.repositories.UserRepository;
import io.github.ms.quarkus.vinicius.service.UserService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class UserResource {

    private UserRepository userRepository;

    @Inject
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger logger = LogManager.getLogger(UserResource.class);

    @POST
    public Response createUser( UserRequest userRequest ) {
        log.info("Obtendo resposta do serviço de criação de usuario...{}", userRequest);
        return userRepository.createUser(userRequest);
    }

    @GET
    public Response listAllUsers() {
        log.info("Obtendo resposta do serviço de listagem de todos os usuarios...");
        return userRepository.listAllUsers();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id, UserRequest userRequest ) {

        log.info("Obtendo resposta do serviço de atualização total do usuario com o ID" + id + "e corpo "+ userRequest
                                + "{}...{}", id, userRequest);
        return userRepository.updateUser(id, userRequest);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser( @PathParam("id") Long id ) {
        log.info("Removendo Usuario de ID:..." + id +"{}", id);
        return userRepository.deleteUser(id);
    }
}
