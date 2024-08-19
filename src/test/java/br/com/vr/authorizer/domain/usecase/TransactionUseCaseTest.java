package br.com.vr.authorizer.domain.usecase;


import br.com.vr.authorizer.infra.adapter.dao.port.CreditCardPort;
import br.com.vr.authorizer.infra.adapter.dao.port.TransactionPort;
import br.com.vr.authorizer.api.v1.dto.TransactionDTO;
import br.com.vr.authorizer.api.v1.dto.TransactionRequest;
import br.com.vr.authorizer.domain.port.repository.TransactionRepositoryPort;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class TransactionUseCaseTest {

    @InjectMocks
    private TransactionUseCase service;

    @Mock
    private TransactionRepositoryPort repository;

    @Test
    void whenCallStartTransactionWithValidNumberCardThenReturnIdTransaction() throws NotFoundException {
        when(repository.startTransaction(any())).thenReturn("123123123");
        final String response = service.startTransaction(new TransactionRequest("1233.1234.1233", "123123", BigDecimal.TEN));
        assertNotNull(response);
    }

    @Test
    void whenCallFindTransactionByNumberCardWithValidNumberCardThenReturnTransactionDTO() throws NotFoundException {
        TransactionPort port = TransactionPort.builder()
                ._id("123")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .creditCardPort(CreditCardPort.builder()
                        ._id("123")
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .numberCard("123.123.123.123")
                        .password("123")
                        .available(BigDecimal.TEN)
                        .build())
                .dataTransaction(LocalDateTime.now())
                .transactionCode(123)
                .transactionValue(BigDecimal.ONE)
                .build();
        when(repository.getListTransaction(anyString(), anyString())).thenReturn(Collections.singletonList(port));
        when(repository.getAvailableCreditCard(anyString(), anyString())).thenReturn(BigDecimal.TEN);
        final TransactionDTO response = service.getListTransaction("123.123.123.123", "1234");

        assertNotNull(response);
        assertEquals(TransactionDTO.class, response.getClass());
        verify(repository).getListTransaction(anyString(), anyString());
        verify(repository).getAvailableCreditCard(anyString(), anyString());
    }
}
