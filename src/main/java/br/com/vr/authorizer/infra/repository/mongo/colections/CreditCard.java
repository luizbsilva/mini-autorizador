package br.com.vr.authorizer.infra.repository.mongo.colections;

import br.com.vr.authorizer.util.ConstantsColections;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = ConstantsColections.COLECTION_CREDITCARD)
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard extends BaseDomain {

    private String numberCard;
    private String password;
    private BigDecimal available;

    @Builder
    public CreditCard(String _id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, LocalDateTime deleted, String numberCard, String password, BigDecimal available) {
        super(_id, createdDate, lastModifiedDate, deleted);
        this.numberCard = numberCard;
        this.password = password;
        this.available = available;
    }
}
