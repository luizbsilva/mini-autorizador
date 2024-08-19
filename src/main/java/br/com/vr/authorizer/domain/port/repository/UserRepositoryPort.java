package br.com.vr.authorizer.domain.port.repository;

import br.com.vr.authorizer.infra.adapter.dao.port.UserPort;

public interface UserRepositoryPort {
    UserPort findByEmail(String email);
}
