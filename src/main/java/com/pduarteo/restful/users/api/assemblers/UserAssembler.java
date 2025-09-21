package com.pduarteo.restful.users.api.assemblers;

import com.pduarteo.restful.users.api.controllers.UserControllerV1;
import com.pduarteo.restful.users.api.dto.UserResponse;
import com.pduarteo.restful.users.api.dto.UserUpdateRequest;
import com.pduarteo.restful.users.domain.User;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAssembler {

    public UserResponse toModel(User user){
        var self = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UserControllerV1.class).getById(user.getId(), null))
                .withSelfRel()
                .withType("application/vnd.pduarteo.user.v1+json");

        var update = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UserControllerV1.class).update(user.getId(), null, null))
                .withRel("update")
                .withType("application/vnd.pduarteo.user.v1+json");

        var delete = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UserControllerV1.class).delete(user.getId()))
                .withRel("delete");

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                List.of(self, update, delete)
        );
    }
}
