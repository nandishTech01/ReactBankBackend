package com.WalletApp.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationErrorService {

	public ResponseEntity<?>  validate(BindingResult result){
		
		if (result.hasErrors()) {
			HashMap<String, String> errmap = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errmap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errmap, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
	
}
