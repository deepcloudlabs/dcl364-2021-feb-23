package com.example.stockmarket.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp="^[a-zA-Z]{3,8}$")
public @interface StockSymbol {
	String message() default "This is not a valid stock symbol.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
