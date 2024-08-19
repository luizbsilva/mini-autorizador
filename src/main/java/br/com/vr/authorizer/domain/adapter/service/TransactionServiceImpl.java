package br.com.vr.authorizer.domain.adapter.service;

import br.com.vr.authorizer.domain.TransactionPort;
import br.com.vr.authorizer.domain.dto.TransactionDTO;
import br.com.vr.authorizer.domain.dto.TransactionRequest;
import br.com.vr.authorizer.domain.port.adapter.TransactionServicePort;
import br.com.vr.authorizer.domain.port.repository.TransactionRepositoryPort;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionServicePort {

    private final TransactionRepositoryPort repositoryPort;


    @Override
    public String startTransaction(TransactionRequest request) throws NotFoundException {
        return repositoryPort.startTransaction(new TransactionPort(request));
    }

    @Override
    public TransactionDTO getListTransaction(String numberCard, String password) throws NotFoundException {
        List<TransactionPort> transactionPorts = repositoryPort.getListTransaction(numberCard, password);
        return new TransactionDTO(repositoryPort.getAvailableCreditCard(numberCard, password),
                transactionPorts.stream().map(TransactionPort::toItem).collect(Collectors.toList()));
    }
}

