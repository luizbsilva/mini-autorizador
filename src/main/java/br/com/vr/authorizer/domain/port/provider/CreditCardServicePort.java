package br.com.vr.authorizer.domain.port.provider;

import br.com.vr.authorizer.api.v1.dto.CreditCartDTO;
import br.com.vr.authorizer.api.v1.dto.CreditCartRequest;
import br.com.vr.authorizer.util.exception.CreditCardAlreadyRegisteredException;
import br.com.vr.authorizer.util.exception.NonExistingCardException;

import java.math.BigDecimal;

public interface CreditCardServicePort {

    CreditCartDTO findByNumberCard(String numberCard) throws NonExistingCardException;

    BigDecimal getAvailableCreditCard(String numberCard, String password) throws NonExistingCardException;

    CreditCartDTO createdCreditCard(CreditCartRequest request) throws CreditCardAlreadyRegisteredException;

    CreditCartDTO changePassword(CreditCartRequest request, String newPassword) throws NonExistingCardException;
}
