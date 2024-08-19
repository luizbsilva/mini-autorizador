package br.com.vr.authorizer.domain.port.provider;

import br.com.vr.authorizer.api.v1.dto.TransactionDTO;
import br.com.vr.authorizer.api.v1.dto.TransactionRequest;
import javassist.NotFoundException;

public interface TransactionServicePort {

    String startTransaction(TransactionRequest request) throws NotFoundException;

    TransactionDTO getListTransaction(String numberCard, String password) throws NotFoundException;
}
