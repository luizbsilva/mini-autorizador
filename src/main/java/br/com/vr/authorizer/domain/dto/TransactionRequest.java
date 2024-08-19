package br.com.vr.authorizer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest extends CreditCartRequest {
    private BigDecimal transactionValue;

    public TransactionRequest(String numberCard, String password, BigDecimal transactionValue) {
        super(numberCard, password);
        this.transactionValue = transactionValue;
    }
}
