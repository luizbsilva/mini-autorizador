package br.com.vr.authorizer.domain.adapter.service;

import br.com.vr.authorizer.domain.UserPort;
import br.com.vr.authorizer.domain.dto.UserDataDTO;
import br.com.vr.authorizer.domain.port.adapter.UserServicePort;
import br.com.vr.authorizer.domain.port.repository.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserServicePort {
    private final UserRepositoryPort repository;

    @Override
    public Optional<UserDataDTO> findByEmail(String email) {
        UserPort user = repository.findByEmail(email);
        return Optional.ofNullable(user.toUser());
    }
}
