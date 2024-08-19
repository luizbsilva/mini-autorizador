package br.com.vr.authorizer.infra.adapter.dao;

import br.com.vr.authorizer.infra.adapter.dao.port.UserPort;
import br.com.vr.authorizer.domain.port.repository.UserRepositoryPort;
import br.com.vr.authorizer.infra.repository.mongo.colections.User;
import br.com.vr.authorizer.infra.repository.mongo.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserDao implements UserRepositoryPort {

    private UserRepository userRepository;

    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPort findByEmail(String email) {
        User userEntity = this.userRepository.findByEmail(email);
        return userEntity.toUser();
    }
}
