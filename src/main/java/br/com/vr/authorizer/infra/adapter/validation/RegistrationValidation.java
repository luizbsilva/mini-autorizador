package br.com.vr.authorizer.infra.adapter.validation;

import br.com.vr.authorizer.infra.repository.mongo.colections.CreditCard;
import br.com.vr.authorizer.infra.repository.mongo.CreditCardRepository;
import br.com.vr.authorizer.util.exception.CreditCardAlreadyRegisteredException;
import br.com.vr.authorizer.util.exception.NonExistingCardException;
import br.com.vr.authorizer.util.exception.dto.ErrorInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RegistrationValidation implements Validation<CreditCard> {
    public static final String CREDIT_CARD_ALREADY_REGISTERED = "Credit Card Already Registered";
    private CreditCardRepository repository;

    public RegistrationValidation(CreditCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public void isValid(Optional<CreditCard> res) throws NonExistingCardException {
        CreditCard creditCard = res.orElseThrow(NonExistingCardException::new);
        List<ErrorInfo> listError = new ArrayList<>();

        Optional<CreditCard> creditCardOld = repository.findByNumberCard(creditCard.getNumberCard());

        if (creditCardOld.isPresent()) {
            listError.add(new ErrorInfo(CREDIT_CARD_ALREADY_REGISTERED));
        }
        if (!listError.isEmpty()) {
            throw new CreditCardAlreadyRegisteredException();
        }

    }
}
