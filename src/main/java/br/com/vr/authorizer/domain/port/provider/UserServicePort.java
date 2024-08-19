package br.com.vr.authorizer.domain.port.provider;

import br.com.vr.authorizer.api.v1.dto.UserDataDTO;

import java.util.Optional;

public interface UserServicePort {

    Optional<UserDataDTO> findByEmail(String email);
}
