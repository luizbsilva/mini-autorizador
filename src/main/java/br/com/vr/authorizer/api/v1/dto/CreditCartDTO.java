package br.com.vr.authorizer.api.v1.dto;

import br.com.vr.authorizer.infra.adapter.dao.port.CreditCardPort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCartDTO {
    private String numberCard;
    private String password;
    private BigDecimal available;

    public CreditCartDTO(CreditCardPort creditCard, String password) {
        this.numberCard = creditCard.getNumberCard();
        this.password = password;
        this.available = creditCard.getAvailable();


    }
}
