package com.com.bestaone.aiwan.account.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.com.bestaone.aiwan.account.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	@Autowired
	private HelloService helloService;
	
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		helloService.greeting("tom");
		System.out.println(value);
		return true;
	}

}
