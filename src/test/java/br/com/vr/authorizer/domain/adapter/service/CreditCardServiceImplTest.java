package br.com.vr.authorizer.domain.adapter.service;

import br.com.vr.authorizer.domain.CreditCardPort;
import br.com.vr.authorizer.domain.dto.CreditCartDTO;
import br.com.vr.authorizer.domain.dto.CreditCartRequest;
import br.com.vr.authorizer.domain.port.repository.CreditCardRepositoryPort;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class CreditCardServiceImplTest {

    @InjectMocks
    private CreditCardServiceImpl service;

    @Mock
    private CreditCardRepositoryPort repository;

    @Test
    void whenCallFindByNumberCardWithValidIdThenReturnCreditCartDTO() throws NotFoundException {
        CreditCardPort port = CreditCardPort.builder()
                ._id("123")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .numberCard("123.123.123.123")
                .password("123")
                .available(BigDecimal.TEN)
                .build();
        when(repository.findByNumberCard(anyString())).thenReturn(port);
        final CreditCartDTO response = service.findByNumberCard("123.123.123.123");

        assertNotNull(response);
        assertEquals(CreditCartDTO.class, response.getClass());
        verify(repository).findByNumberCard(anyString());
    }


    @Test
    void whenCallAvailableCreditCard() throws NotFoundException {
        when(repository.getAvailableCreditCard(anyString(), anyString())).thenReturn(BigDecimal.TEN);

        final BigDecimal response = service.getAvailableCreditCard("123.123.123.123", "123123");

        assertNotNull(response);
        assertEquals(BigDecimal.class, response.getClass());
        verify(repository).getAvailableCreditCard(anyString(), anyString());
    }

    @Test
    void whenCallCreatedCreditCarddWithValidThenReturnCreditCartDTO() throws NotFoundException {
        CreditCardPort port = CreditCardPort.builder()
                ._id("123")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .numberCard("123.123.123.123")
                .password("123")
                .available(BigDecimal.TEN)
                .build();
        CreditCartRequest request = CreditCartRequest.builder()
                .numberCard("123.123.123.123")
                .password("123")
                .build();

        when(repository.createdCreditCard(any())).thenReturn(port);
        final CreditCartDTO response = service.createdCreditCard(request);

        assertNotNull(response);
        assertEquals(CreditCartDTO.class, response.getClass());
        verify(repository).createdCreditCard(any());
    }

    @Test
    void whenCallChangePasswordWithValidThenReturnCreditCartDTO() throws NotFoundException {
        CreditCardPort port = CreditCardPort.builder()
                ._id("123")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .numberCard("123.123.123.123")
                .password("123")
                .available(BigDecimal.TEN)
                .build();
        CreditCartRequest request = CreditCartRequest.builder()
                .numberCard("123.123.123.123")
                .password("123")
                .build();

        when(repository.changePassword(any(), anyString())).thenReturn(port);
        final CreditCartDTO response = service.changePassword(request, "1234");

        assertNotNull(response);
        assertEquals(CreditCartDTO.class, response.getClass());
        verify(repository).changePassword(any(), anyString());
    }
}
