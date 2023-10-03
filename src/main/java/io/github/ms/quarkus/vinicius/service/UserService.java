package io.github.ms.quarkus.vinicius.service;

import io.github.ms.quarkus.vinicius.model.UserModel;
import io.github.ms.quarkus.vinicius.quarkusproject.rest.errors.ResponseError;
import io.github.ms.quarkus.vinicius.quarkusproject.rest.request.UserRequest;
import io.github.ms.quarkus.vinicius.repositories.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@ApplicationScoped
@Slf4j
public class UserService implements UserRepository , PanacheRepository<UserModel>{
    private Validator validator;
    private UserService userService;
    @Inject
    public UserService(Validator validator, UserService userService) {
        this.validator = validator;
        this.userService = userService;
    }

    @Transactional
    @Override
    public Response createUser(UserRequest userRequest) {
        Set<ConstraintViolation<UserRequest>> validateBody = validator.validate(userRequest);

        if (!validateBody.isEmpty()) {
            ResponseError errorMessage = ResponseError.createFromValidation(validateBody);
            return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
        }
        UserModel userModel = new UserModel();

        userModel.setName(userRequest.getName());
        userModel.setEmailAddress(userRequest.getEmailAddress());
        userModel.setRg(userRequest.getRg());
        userModel.setCpf(userRequest.getCpf());

        // persistindo com panache base entity
        userService.persist(userModel);
        log.info("Usuário Criado com Sucesso....{}", userModel);
        return Response.status(Response.Status.CREATED).entity(userModel).build();
    }

    @Override
    public Response listAllUsers() {
        PanacheQuery<UserModel> users = userService.findAll();
        if (users.list().isEmpty()) {
            log.info("Usuário não encontrado....");
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        log.info("Usuario Encontrado....");
        return Response.ok(users.list()).build();
    }

    @Override
    @Transactional
    public Response updateUser(Long id, UserRequest userRequest) {
        log.info("Atualizando Usuario de ID: " + id + "..{}", id);
        UserModel user = userService.findById(id);

        if (user != null) {
            user.setName(userRequest.getName());
            user.setEmailAddress(userRequest.getEmailAddress());
            user.setRg(userRequest.getRg());
            user.setCpf(userRequest.getCpf());
            return Response.ok(userRequest).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response deleteUser(Long id) {
        UserModel userModel = userService.findById(id);

        if (userModel != null) {
            userService.delete(userModel);
            return Response.noContent().build();
        }
        log.info("Usuário não encontrado...");
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
