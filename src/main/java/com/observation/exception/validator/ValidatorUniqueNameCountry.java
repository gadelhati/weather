package com.observation.exception.validator;

import com.observation.exception.annotation.UniqueNameCountry;
import com.observation.persistence.payload.request.DTORequestCountry;
import com.observation.service.ServiceCountry;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorUniqueNameCountry implements ConstraintValidator<UniqueNameCountry, DTORequestCountry> {

    @Autowired
    private ServiceCountry serviceCountry;

    @Override
    public void initialize(UniqueNameCountry constraintAnnotation) {
    }
    @Override
    public boolean isValid(DTORequestCountry value, ConstraintValidatorContext context) {
        if (!Validator.isNull(value.getName()) && !serviceCountry.existsByName(value.getName()) ||
                !Validator.isNull(value.getName()) && !Validator.isNull(value.getId()) && !serviceCountry.existsByNameAndIdNot(value.getName(), value.getId()) ) {
            return true;
        } else {
            return false;
        }
    }
}