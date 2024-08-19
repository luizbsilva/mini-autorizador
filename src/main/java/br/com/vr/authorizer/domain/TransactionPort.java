package br.com.vr.authorizer.domain;

import br.com.vr.authorizer.domain.dto.ItemTransaction;
import br.com.vr.authorizer.domain.dto.TransactionRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NotBlank
@Data
public class TransactionPort {

    private String _id;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private LocalDateTime deleted;

    private CreditCardPort creditCardPort;

    private LocalDateTime dataTransaction;

    private Integer transactionCode;

    private BigDecimal transactionValue;

    public TransactionPort(String _id, LocalDateTime dataTransaction, Integer transactionCode, BigDecimal transactionValue) {
        this._id = _id;
        this.dataTransaction = dataTransaction;
        this.transactionCode = transactionCode;
        this.transactionValue = transactionValue;
    }

    public TransactionPort(TransactionRequest request) {
        this.createdDate = LocalDateTime.now();
        this.creditCardPort = new CreditCardPort(request.getNumberCard(), request.getPassword());
        this.dataTransaction = LocalDateTime.now();
        this.transactionValue = request.getTransactionValue();
    }

    public ItemTransaction toItem() {
        return new ItemTransaction(this.transactionCode, this.dataTransaction, this.transactionValue);
    }

}