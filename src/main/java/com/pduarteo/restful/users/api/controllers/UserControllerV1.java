package com.pduarteo.restful.users.api.controllers;

import com.pduarteo.restful.users.api.MediaTypesV1;
import com.pduarteo.restful.users.api.assemblers.UserAssembler;
import com.pduarteo.restful.users.api.dto.UserCreateRequest;
import com.pduarteo.restful.users.api.dto.UserResponse;
import com.pduarteo.restful.users.api.dto.UserUpdateRequest;
import com.pduarteo.restful.users.domain.User;
import com.pduarteo.restful.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/users", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypesV1.VND_USER_V1})
@RequiredArgsConstructor
public class UserControllerV1 {

    private final UserService service;
    private final UserAssembler assembler;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaTypesV1.VND_USER_V1})
    public ResponseEntity<UserResponse> create(
            @RequestBody @Valid UserCreateRequest body,
            UriComponentsBuilder uriBuilder) {
        User created = service.create(body);
        UserResponse model = assembler.toModel(created);

        var location = uriBuilder
                .path("/api/v1/users/{id}")
                .build(created.getId());

        return ResponseEntity
                .created(location)
                .cacheControl(CacheControl.noStore())
                .contentType(MediaType.APPLICATION_JSON)
                .body(model);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(
            @PathVariable UUID id,
            @RequestHeader(value = "Accept", required = false) String accept) {

        var user = service.findOrThrow(id);
        var model = assembler.toModel(user);

        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.noCache())
                .contentType(MediaType.APPLICATION_JSON)
                .body(model);
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaTypesV1.VND_USER_V1})
    public ResponseEntity<UserResponse> update (
            @PathVariable UUID id,
            @RequestBody @Valid UserUpdateRequest body,
            UriComponentsBuilder uriBuilder) {
        User updated = service.update(id, body);
        UserResponse model = assembler.toModel(updated);

        var location = uriBuilder
                .path("/api/v1/users/{id}")
                .build(updated.getId());

        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.noCache())
                .contentType(MediaType.APPLICATION_JSON)
                .body(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity
                .noContent()
                .cacheControl(CacheControl.noCache())
                .build();
    }
}
