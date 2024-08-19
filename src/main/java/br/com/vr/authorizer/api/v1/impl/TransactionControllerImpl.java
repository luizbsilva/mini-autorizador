package br.com.vr.authorizer.api.v1.impl;

import br.com.vr.authorizer.api.v1.TransactionController;
import br.com.vr.authorizer.api.v1.dto.TransactionDTO;
import br.com.vr.authorizer.api.v1.dto.TransactionRequest;
import br.com.vr.authorizer.domain.port.provider.TransactionServicePort;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransactionControllerImpl implements TransactionController {

    private TransactionServicePort servicePort;

    @Override
    public ResponseEntity<String> startTransaction(@RequestBody TransactionRequest request) throws NotFoundException {
        servicePort.startTransaction(request);
        return ResponseEntity.ok("OK");
    }

    @Override
    public ResponseEntity<TransactionDTO> getListTransaction(@PathVariable String cardNumber, @PathVariable String password) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(servicePort.getListTransaction(cardNumber, password));
    }
}
