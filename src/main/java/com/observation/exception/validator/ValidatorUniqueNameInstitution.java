package com.observation.exception.validator;

import com.observation.exception.annotation.UniqueNameInstitution;
import com.observation.persistence.payload.request.DTORequestInstitution;
import com.observation.service.ServiceInstitution;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorUniqueNameInstitution implements ConstraintValidator<UniqueNameInstitution, DTORequestInstitution> {

    @Autowired
    private ServiceInstitution serviceInstitution;

    @Override
    public void initialize(UniqueNameInstitution constraintAnnotation) {
    }
    @Override
    public boolean isValid(DTORequestInstitution value, ConstraintValidatorContext context) {
        if (!Validator.isNull(value.getName()) && !serviceInstitution.existsByName(value.getName()) ||
                !Validator.isNull(value.getName()) && !Validator.isNull(value.getId()) && !serviceInstitution.existsByNameAndIdNot(value.getName(), value.getId()) ) {
            return true;
        } else {
            return false;
        }
    }
}