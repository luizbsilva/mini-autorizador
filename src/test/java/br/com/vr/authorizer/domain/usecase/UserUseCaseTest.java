package br.com.vr.authorizer.domain.usecase;

import br.com.vr.authorizer.infra.adapter.dao.port.UserPort;
import br.com.vr.authorizer.api.v1.dto.UserDataDTO;
import br.com.vr.authorizer.domain.port.repository.UserRepositoryPort;
import br.com.vr.authorizer.infra.repository.mongo.enums.ProfileEnum;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class UserUseCaseTest {

    @InjectMocks
    private UserUseCase service;

    @Mock
    private UserRepositoryPort repository;

    @Test
    void whenCallFindUserByEmailWithValidEmailThenReturnUserDataDTO() {
        UserPort port = UserPort.builder()
                ._id("123")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .name("Admin")
                .email("admin@teste.com")
                .password("1234")
                .profile(ProfileEnum.ROLE_ADM)
                .active(Boolean.TRUE)
                .build();
        when(repository.findByEmail(anyString())).thenReturn(port);
        final Optional<UserDataDTO> response = service.findByEmail("teste@email.com");

        assertNotNull(response);
        assertEquals(Optional.class, response.getClass());
        verify(repository).findByEmail(anyString());
    }
}
