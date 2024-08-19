package br.com.vr.authorizer.infra.adapter.dao;

import br.com.vr.authorizer.infra.adapter.dao.port.CreditCardPort;
import br.com.vr.authorizer.infra.adapter.validation.RegistrationValidation;
import br.com.vr.authorizer.infra.repository.mongo.CreditCardRepository;
import br.com.vr.authorizer.infra.repository.mongo.colections.CreditCard;
import br.com.vr.authorizer.infra.repository.mongo.mapper.CreditCardMapper;
import br.com.vr.authorizer.util.UUIDUtils;
import br.com.vr.authorizer.util.exception.NonExistingCardException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class CreditCardDaoTest {

    @InjectMocks
    private CreditCardDao dao;
    @Mock
    private CreditCardRepository repository;
    @Mock
    private RegistrationValidation validation;
    @Mock
    private CreditCardMapper mapper;

    @Test
    void whenCallFindByNumberCardWithValidThenReturnCreditCardPort() {

        when(repository.findByNumberCard(anyString())).thenReturn(Optional.of(createCreditCard()));
        when(mapper.toModel(any())).thenReturn(createCreditCardPort());
        final CreditCardPort response = dao.findByNumberCard("123.123.123.123");

        assertNotNull(response);
        assertEquals(CreditCardPort.class, response.getClass());
        verify(repository).findByNumberCard(anyString());
    }

    @Test
    void whenCallFindByNumberCardWithValidThenReturnNonExistingCardException() throws NonExistingCardException {
        when(repository.findByNumberCard(anyString())).thenReturn(null);
        Assertions.assertThrows(NonExistingCardException.class,
                () -> dao.findByCreditCard(null));
    }

    @Test
    void testSaveCreditCardSucessThenReturnCreditCardPort() {
        CreditCard creditCard = createCreditCard();
        CreditCardPort creditCardPort = createCreditCardPort();

        when(mapper.toDomain(any(CreditCardPort.class))).thenReturn(creditCard);
        when(repository.save(any(CreditCard.class))).thenReturn(creditCard);
        when(mapper.toModel(any(CreditCard.class))).thenReturn(creditCardPort);

        CreditCardPort credictCardPersisted = dao.createdCreditCard(creditCardPort);

        assertAll(
                () -> Assertions.assertNotNull(credictCardPersisted.get_id()),
                () -> Assertions.assertNotNull(credictCardPersisted.getCreatedDate())

        );

    }

    private CreditCard createCreditCard() {
        return CreditCard.builder()
                ._id(UUIDUtils.getUUID())
                .numberCard("123.123.123.123")
                .password(new BCryptPasswordEncoder().encode("123"))
                .available(BigDecimal.TEN)
                .createdDate(LocalDateTime.now())
                .build();
    }

    private CreditCardPort createCreditCardPort() {
        return CreditCardPort.builder()
                ._id("123")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .numberCard("123.123.123.123")
                .password("123")
                .available(BigDecimal.TEN)
                .build();
    }
}
