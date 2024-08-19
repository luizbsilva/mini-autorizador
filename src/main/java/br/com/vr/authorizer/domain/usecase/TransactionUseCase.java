package br.com.vr.authorizer.domain.usecase;

import br.com.vr.authorizer.infra.adapter.dao.port.TransactionPort;
import br.com.vr.authorizer.api.v1.dto.TransactionDTO;
import br.com.vr.authorizer.api.v1.dto.TransactionRequest;
import br.com.vr.authorizer.domain.port.provider.TransactionServicePort;
import br.com.vr.authorizer.domain.port.repository.TransactionRepositoryPort;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionUseCase implements TransactionServicePort {

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

