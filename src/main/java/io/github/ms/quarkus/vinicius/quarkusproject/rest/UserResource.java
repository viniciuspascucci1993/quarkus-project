package io.github.ms.quarkus.vinicius.quarkusproject.rest;

import io.github.ms.quarkus.vinicius.model.UserModel;
import io.github.ms.quarkus.vinicius.quarkusproject.rest.request.UserRequest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    @Transactional
    public Response createUser( UserRequest userRequest ) {
        UserModel userModel = new UserModel();

        userModel.setName(userRequest.getName());
        userModel.setEmailAddress(userRequest.getEmailAddress());
        userModel.setRg(userRequest.getRg());
        userModel.setCpf(userRequest.getCpf());

        userModel.persist();

        return Response.ok(userRequest).build();
    }

    @GET
    public Response listAllUsers() {
        return Response.ok().build();
    }
}
