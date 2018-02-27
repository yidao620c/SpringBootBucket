package com.xncoding.trans.modules;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * MyBeanValidator
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/3
 */
public class MyBeanValidator<T> implements Validator<T>, InitializingBean {
    private javax.validation.Validator validator;

    @Override
    public void validate(T value) throws ValidationException {
        /*
         * 使用Validator的validate方法校验数据
         */
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(value);
        if (constraintViolations.size() > 0) {
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                message.append(constraintViolation.getMessage()).append("\n");
            }
            throw new ValidationException(message.toString());
        }
    }

    /**
     * 使用JSR-303的Validator来校验我们的数据，在此进行JSR-303的Validator的初始化
     */
    @Override
    public void afterPropertiesSet() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }
}
