package br.com.vr.authorizer.domain.usecase;

import br.com.vr.authorizer.infra.adapter.dao.port.CreditCardPort;
import br.com.vr.authorizer.api.v1.dto.CreditCartDTO;
import br.com.vr.authorizer.api.v1.dto.CreditCartRequest;
import br.com.vr.authorizer.domain.port.provider.CreditCardServicePort;
import br.com.vr.authorizer.domain.port.repository.CreditCardRepositoryPort;
import br.com.vr.authorizer.util.exception.CreditCardAlreadyRegisteredException;
import br.com.vr.authorizer.util.exception.NonExistingCardException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CreditCardUseCase implements CreditCardServicePort {

    private final CreditCardRepositoryPort repositoryPort;


    @Override
    public CreditCartDTO findByNumberCard(String numberCard) throws NonExistingCardException {
        CreditCardPort creditCardPort = repositoryPort.findByNumberCard(numberCard);
        return new CreditCartDTO(creditCardPort, "");
    }

    @Override
    public BigDecimal getAvailableCreditCard(String numberCard, String password) throws NonExistingCardException {
        return repositoryPort.getAvailableCreditCard(numberCard, password);
    }

    @Override
    public CreditCartDTO createdCreditCard(CreditCartRequest request) throws CreditCardAlreadyRegisteredException {
        CreditCardPort creditCard = repositoryPort.createdCreditCard(new CreditCardPort(request));
        return new CreditCartDTO(creditCard, request.getPassword());
    }

    @Override
    public CreditCartDTO changePassword(CreditCartRequest request, String newPassword) throws NonExistingCardException {
        CreditCardPort creditCard = repositoryPort.changePassword(new CreditCardPort(request.getNumberCard(), request.getPassword()), newPassword);
        return new CreditCartDTO(creditCard, request.getPassword());
    }
}
