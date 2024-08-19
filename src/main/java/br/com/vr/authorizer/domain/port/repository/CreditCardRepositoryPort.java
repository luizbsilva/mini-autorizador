package br.com.vr.authorizer.domain.port.repository;

import br.com.vr.authorizer.infra.adapter.dao.port.CreditCardPort;
import br.com.vr.authorizer.infra.repository.mongo.colections.CreditCard;
import br.com.vr.authorizer.util.exception.CreditCardAlreadyRegisteredException;
import br.com.vr.authorizer.util.exception.NonExistingCardException;
import javassist.NotFoundException;

import java.math.BigDecimal;

public interface CreditCardRepositoryPort {
    CreditCardPort findByNumberCard(String numberCard) throws NonExistingCardException;

    CreditCardPort createdCreditCard(CreditCardPort creditCardPort) throws CreditCardAlreadyRegisteredException;

    CreditCardPort changePassword(CreditCardPort creditCardPort, String newPassword) throws NonExistingCardException;

    CreditCardPort changeCreditCardBalance(CreditCardPort creditCardPort);

    BigDecimal getAvailableCreditCard(String numberCard, String password) throws NonExistingCardException;

    void getValidatePassword(String passwordRequest, String password);

    CreditCard getCreditCard(String idCreditCard) throws NotFoundException;

    CreditCard getCreditCardByNumberCard(String numberCard) throws NotFoundException;
}
