package com.pduarteo.restful.users.service;

import com.pduarteo.restful.users.api.dto.UserCreateRequest;
import com.pduarteo.restful.users.api.dto.UserUpdateRequest;
import com.pduarteo.restful.users.domain.User;
import com.pduarteo.restful.users.repo.UserRepository;
import com.pduarteo.restful.users.support.exceptions.EmailAlreadyUsedException;
import com.pduarteo.restful.users.support.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    @Transactional
    public User create(UserCreateRequest req) {
        if(repo.existsByEmail(req.email())){
            throw new EmailAlreadyUsedException(req.email());
        }

        var user = new User();
        user.setName(req.name());
        user.setEmail(req.email());
        user.setPasswordHash(req.passwordHash());
        return repo.save(user);
    }

    @Transactional
    public User update (UUID id, UserUpdateRequest req) {
        var update = repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        update.setName(req.name());
        update.setEmail(req.email());
        return repo.save(update);
    }

    @Transactional
    public void delete(UUID id) {
        repo.deleteById(id);
    }

    @Transactional
    public User findOrThrow(UUID id) {
        return repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
