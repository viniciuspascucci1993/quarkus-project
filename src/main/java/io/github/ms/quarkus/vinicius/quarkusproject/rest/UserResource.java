package io.github.ms.quarkus.vinicius.quarkusproject.rest;

import io.github.ms.quarkus.vinicius.quarkusproject.rest.request.UserRequest;
import io.github.ms.quarkus.vinicius.repositories.UserRepository;
import io.github.ms.quarkus.vinicius.service.UserService;
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

    /*@GET
    public Response listAllUsers() {
        PanacheQuery<UserModel> users = userRepository.findAll();
        if (users != null) {
            return Response.ok(users.list()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser(@PathParam("id") Long id, UserRequest userRequest ) {

        UserModel user = userRepository.findById(id);

        if (user != null) {
            user.setName(userRequest.getName());
            user.setEmailAddress(userRequest.getEmailAddress());
            user.setRg(userRequest.getRg());
            user.setCpf(userRequest.getCpf());
            return Response.ok(userRequest).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();  
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser( @PathParam("id") Long id ) {
        UserModel userModel = userRepository.findById(id);

        if (userModel != null) {
            userRepository.delete(userModel);
            return Response.noContent().build();
        }
        logger.info("Usuário não encontrado...{}", userModel);
        return Response.status(Response.Status.NOT_FOUND).build();
    }*/
}
