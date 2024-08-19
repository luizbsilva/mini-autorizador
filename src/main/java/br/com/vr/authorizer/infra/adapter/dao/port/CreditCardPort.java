package br.com.vr.authorizer.infra.adapter.dao.port;

import br.com.vr.authorizer.api.v1.dto.CreditCartDTO;
import br.com.vr.authorizer.api.v1.dto.CreditCartRequest;
import br.com.vr.authorizer.infra.repository.mongo.colections.CreditCard;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NotBlank
@Data
public class CreditCardPort {

    public static final long NEW_AVAILABLE = 500L;
    private String _id;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private LocalDateTime deleted;

    private String numberCard;
    private String password;

    private BigDecimal available;

    public CreditCardPort() {
    }

    public CreditCardPort(CreditCartRequest request) {
        this.numberCard = request.getNumberCard();
        this.password = encodPassword(request.getPassword());
        this.available = newAvailableValue();
    }

    public CreditCardPort(String numberCard, String password) {
        this.numberCard = numberCard;
        this.password = password;
    }

    public CreditCardPort(CreditCard creditCard) {
        this.numberCard = creditCard.getNumberCard();
        this.available = creditCard.getAvailable();
    }

    private String encodPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private BigDecimal newAvailableValue() {
        return BigDecimal.valueOf(NEW_AVAILABLE);
    }

    public CreditCardPort(String _id, LocalDateTime createdDate, LocalDateTime lastModifiedDate, LocalDateTime deleted, String numberCard, String password, BigDecimal available) {
        this._id = _id;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.deleted = deleted;
        this.numberCard = numberCard;
        this.password = password;
        this.available = available;
    }

    public CreditCartDTO toCreditCard() {
        return new CreditCartDTO(this.numberCard, this.password, this.available);
    }


}
