package com.observation.exception.validator;

import com.observation.exception.annotation.UniqueNIPObserver;
import com.observation.persistence.payload.request.DTORequestObserver;
import com.observation.service.ServiceObserver;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorUniqueNIPObserver implements ConstraintValidator<UniqueNIPObserver, DTORequestObserver> {

    @Autowired
    private ServiceObserver serviceObserver;

    @Override
    public void initialize(UniqueNIPObserver constraintAnnotation) {
    }
    @Override
    public boolean isValid(DTORequestObserver value, ConstraintValidatorContext context) {
        if (!Validator.isNull(value.getNip()) && !serviceObserver.existsByNIP(value.getNip()) ||
                !Validator.isNull(value.getNip()) && !Validator.isNull(value.getId()) && !serviceObserver.existsByNIPAndIdNot(value.getNip(), value.getId()) ) {
            return true;
        } else {
            return false;
        }
    }
}