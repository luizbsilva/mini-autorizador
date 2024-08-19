package br.com.vr.authorizer.infra.repository.mongo.mapper;

import br.com.vr.authorizer.infra.adapter.dao.port.CreditCardPort;
import br.com.vr.authorizer.infra.repository.mongo.colections.CreditCard;
import br.com.vr.authorizer.util.UUIDUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreditCardMapper implements MapperDomain<CreditCard, CreditCardPort> {

    @Override
    public CreditCardPort toModel(CreditCard creditCard) {
        if (creditCard == null)
            return null;
        return CreditCardPort.builder()
                ._id(creditCard.get_id())
                .createdDate(creditCard.getCreatedDate())
                .lastModifiedDate(creditCard.getLastModifiedDate() != null ? creditCard.getLastModifiedDate() : null)
                .deleted(creditCard.getDeleted() != null ? creditCard.getDeleted() : null)
                .numberCard(creditCard.getNumberCard())
                .password(creditCard.getPassword())
                .available(creditCard.getAvailable())
                .build();
    }

    @Override
    public CreditCard toDomain(CreditCardPort creditCardPort) {
        if (creditCardPort == null)
            return null;

        return CreditCard.builder()
                ._id(null == creditCardPort.get_id() ? UUIDUtils.getUUID() : creditCardPort.get_id())
                .numberCard(creditCardPort.getNumberCard())
                .password(creditCardPort.getPassword())
                .available(creditCardPort.getAvailable())
                .createdDate(LocalDateTime.now())
                .build();
    }
}
