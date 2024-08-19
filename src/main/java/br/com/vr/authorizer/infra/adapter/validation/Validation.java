package br.com.vr.authorizer.infra.adapter.validation;

import br.com.vr.authorizer.util.exception.NonExistingCardException;
import javassist.NotFoundException;

import java.util.Optional;

public interface Validation<T> {
    void isValid(Optional<T> res) throws NonExistingCardException;
}
