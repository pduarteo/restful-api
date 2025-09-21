package com.pduarteo.restful.users.api.dto;

import org.springframework.hateoas.Link;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        OffsetDateTime createdAt,
        List<Link> _links
) {}
