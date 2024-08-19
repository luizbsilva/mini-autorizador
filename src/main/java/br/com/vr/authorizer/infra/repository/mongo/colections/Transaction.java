package br.com.vr.authorizer.infra.repository.mongo.colections;

import br.com.vr.authorizer.infra.adapter.dao.port.TransactionPort;
import br.com.vr.authorizer.util.ConstantsColections;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = ConstantsColections.COLECTION_TRANSACTION)
public class Transaction extends BaseDomain {

    @DBRef
    private CreditCard creditCard;
    private LocalDateTime dataTransaction;
    private Integer transactionCode;
    private BigDecimal transactionValue;

    public TransactionPort toTransactionPort() {
        return new TransactionPort(this._id, this.dataTransaction, this.transactionCode, this.transactionValue);
    }

}
