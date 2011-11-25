package org.tiling.didyoumean.web;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SearchFormValidator implements Validator {

	public boolean supports(Class clazz) {
        return clazz.equals(SearchForm.class);
    }

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "query", "error.blank");
	}

}
