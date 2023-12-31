package com.observation.exception.validator;

import com.observation.exception.annotation.UniqueNameManufacturer;
import com.observation.persistence.payload.request.DTORequestManufacturer;
import com.observation.service.ServiceManufacturer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorUniqueNameManufacturer implements ConstraintValidator<UniqueNameManufacturer, DTORequestManufacturer> {

    @Autowired
    private ServiceManufacturer serviceManufacturer;

    @Override
    public void initialize(UniqueNameManufacturer constraintAnnotation) {
    }
    @Override
    public boolean isValid(DTORequestManufacturer value, ConstraintValidatorContext context) {
        if (!Validator.isNull(value.getName()) && !serviceManufacturer.existsByName(value.getName()) ||
                !Validator.isNull(value.getName()) && !Validator.isNull(value.getId()) && !serviceManufacturer.existsByNameAndIdNot(value.getName(), value.getId()) ) {
            return true;
        } else {
            return false;
        }
    }
}