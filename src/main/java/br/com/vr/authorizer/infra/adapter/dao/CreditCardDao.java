package br.com.vr.authorizer.infra.adapter.dao;

import br.com.vr.authorizer.infra.adapter.dao.port.CreditCardPort;
import br.com.vr.authorizer.infra.repository.mongo.mapper.CreditCardMapper;
import br.com.vr.authorizer.domain.port.repository.CreditCardRepositoryPort;
import br.com.vr.authorizer.infra.repository.mongo.colections.CreditCard;
import br.com.vr.authorizer.infra.repository.mongo.CreditCardRepository;
import br.com.vr.authorizer.util.exception.InvalidPasswordException;
import br.com.vr.authorizer.util.exception.NonExistingCardException;
import br.com.vr.authorizer.infra.adapter.validation.RegistrationValidation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CreditCardDao implements CreditCardRepositoryPort {

    private final CreditCardRepository repository;
    private final RegistrationValidation validation;
    private final CreditCardMapper mapper;

    @Override
    public CreditCardPort findByNumberCard(String numberCard) throws NonExistingCardException {
        return mapper.toModel(findByCreditCard(numberCard));
    }

    public CreditCard findByCreditCard(String numberCard) throws NonExistingCardException {
        Optional<CreditCard> cardOptionalv = repository.findByNumberCard(numberCard);
        return cardOptionalv.orElseThrow(NonExistingCardException::new);
    }

    @Override
    public CreditCardPort createdCreditCard(CreditCardPort creditCardPort) throws NonExistingCardException {
        CreditCard creditCard = mapper.toDomain(creditCardPort);
        validation.isValid(Optional.ofNullable(creditCard));
        CreditCard creditCarddb = repository.save(Objects.requireNonNull(creditCard));
        return mapper.toModel(creditCarddb);
    }

    @Override
    public CreditCardPort changePassword(CreditCardPort creditCardPort, String newPassword) throws NonExistingCardException {
        CreditCard creditCard = findByCreditCard(creditCardPort.getNumberCard());
        getValidatePassword(creditCardPort.getPassword(), creditCard.getPassword());
        creditCard.setPassword(encodPassword(newPassword));
        CreditCard creditCarddb = repository.save(creditCard);
        return mapper.toModel(creditCarddb);
    }

    private String encodPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    @Override
    public CreditCardPort changeCreditCardBalance(CreditCardPort creditCardPort) {
        CreditCard creditCard = mapper.toDomain(creditCardPort);
        repository.save(creditCard);
        return mapper.toModel(creditCard);
    }

    @Override
    public BigDecimal getAvailableCreditCard(String numberCard, String password) throws NonExistingCardException {
        CreditCard creditCard = findByCreditCard(numberCard);
        getValidatePassword(password, creditCard.getPassword());
        return creditCard.getAvailable();
    }

    @Override
    public void getValidatePassword(String passwordRequest, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(passwordRequest, password))
            throw new InvalidPasswordException();
    }

    @Override
    public CreditCard getCreditCard(String idCreditCard) {
        Optional<CreditCard> cardOptionalv = repository.findById(idCreditCard);
        return cardOptionalv.orElseThrow(NonExistingCardException::new);
    }

    @Override
    public CreditCard getCreditCardByNumberCard(String numberCard) throws NotFoundException {
        return findByCreditCard(numberCard);
    }

}
