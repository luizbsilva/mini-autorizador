package br.com.vr.authorizer.infra.repository.mongo;

import br.com.vr.authorizer.infra.repository.mongo.colections.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends MongoRepository<CreditCard, String> {
    Optional<CreditCard> findByNumberCard(String numberCard);
}
