package br.com.vr.authorizer.infra.adapter.dao;

import br.com.vr.authorizer.infra.adapter.dao.port.CreditCardPort;
import br.com.vr.authorizer.infra.adapter.dao.port.TransactionPort;
import br.com.vr.authorizer.infra.repository.mongo.mapper.TransctionMapper;
import br.com.vr.authorizer.domain.port.repository.CreditCardRepositoryPort;
import br.com.vr.authorizer.domain.port.repository.TransactionRepositoryPort;
import br.com.vr.authorizer.infra.repository.mongo.colections.CreditCard;
import br.com.vr.authorizer.infra.repository.mongo.colections.Transaction;
import br.com.vr.authorizer.infra.repository.mongo.TransactionRepository;
import br.com.vr.authorizer.util.exception.InsufficientBalanceException;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TransactionDao implements TransactionRepositoryPort {

    private final CreditCardRepositoryPort creditCardRepositoryPort;
    private final TransactionRepository repository;
    private final TransctionMapper mapper;
    private final ConcurrentHashMap<String, Lock> locks = new ConcurrentHashMap<>();


    @Override
    public String startTransaction(TransactionPort transactionPort) throws NotFoundException {
        Lock lock = locks.computeIfAbsent(transactionPort.get_id(), k -> new ReentrantLock());
        lock.lock();
        try {
            CreditCardPort creditCardPort = creditCardRepositoryPort.findByNumberCard(transactionPort.getCreditCardPort().getNumberCard());
            creditCardRepositoryPort.getValidatePassword(transactionPort.getCreditCardPort().getPassword(), creditCardPort.getPassword());
            validateAvailableBalance(transactionPort.getTransactionValue(), creditCardPort.getAvailable());
            creditCardPort.setAvailable(creditCardPort.getAvailable().subtract(transactionPort.getTransactionValue()));
            creditCardPort = creditCardRepositoryPort.changeCreditCardBalance(creditCardPort);
            Transaction transaction = mapper.toDomain(transactionPort);
            transaction.setCreditCard(creditCardRepositoryPort.getCreditCard(creditCardPort.get_id()));
            repository.save(transaction);
        } finally {
            lock.unlock();
            locks.remove(transactionPort.get_id());
        }
        return transactionPort.get_id();
    }

    @Override
    public List<TransactionPort> getListTransaction(String numberCard, String password) throws NotFoundException {
        CreditCard creditCard = creditCardRepositoryPort.getCreditCardByNumberCard(numberCard);
        creditCardRepositoryPort.getValidatePassword(password, creditCard.getPassword());
        List<Transaction> transaction = repository.findByCreditCard(creditCard);
        return transaction.stream().map(Transaction::toTransactionPort).collect(Collectors.toList());
    }

    @Override
    public BigDecimal getAvailableCreditCard(String numberCard, String password) throws NotFoundException {
        return creditCardRepositoryPort.getAvailableCreditCard(numberCard, password);
    }

    private void validateAvailableBalance(BigDecimal transactionValue, BigDecimal availableBalance) {
        if (availableBalance.compareTo(transactionValue) < 0)
            throw new InsufficientBalanceException();

    }
}
