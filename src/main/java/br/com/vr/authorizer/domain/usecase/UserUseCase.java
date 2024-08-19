package br.com.vr.authorizer.domain.usecase;

import br.com.vr.authorizer.infra.adapter.dao.port.UserPort;
import br.com.vr.authorizer.api.v1.dto.UserDataDTO;
import br.com.vr.authorizer.domain.port.provider.UserServicePort;
import br.com.vr.authorizer.domain.port.repository.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserUseCase implements UserServicePort {
    private final UserRepositoryPort repository;

    @Override
    public Optional<UserDataDTO> findByEmail(String email) {
        UserPort user = repository.findByEmail(email);
        return Optional.ofNullable(user.toUser());
    }
}
