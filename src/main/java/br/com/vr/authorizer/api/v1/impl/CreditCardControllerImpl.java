package br.com.vr.authorizer.api.v1.impl;

import br.com.vr.authorizer.api.v1.CreditCardController;
import br.com.vr.authorizer.domain.dto.CreditCartDTO;
import br.com.vr.authorizer.domain.dto.CreditCartRequest;
import br.com.vr.authorizer.domain.port.adapter.CreditCardServicePort;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class CreditCardControllerImpl implements CreditCardController {

    private CreditCardServicePort servicePort;

    @Override
    public ResponseEntity<CreditCartDTO> createdCreditCard(@RequestBody CreditCartRequest request) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicePort.createdCreditCard(request));
    }

    @Override
    public ResponseEntity<CreditCartDTO> changePassword(@RequestBody CreditCartRequest request, @PathVariable String password) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicePort.changePassword(request, password));
    }

    @Override
    public ResponseEntity<BigDecimal> getAvailableCreditCard(@PathVariable String cardNumber, @PathVariable String password) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(servicePort.getAvailableCreditCard(cardNumber, password));
    }
}
