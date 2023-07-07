package com.WalletApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WalletApp.Services.ValidationErrorService;
import com.WalletApp.Services.WalletService;
import com.WalletApp.entities.Wallet;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/wallet")
@CrossOrigin("http://localhost:5173")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@Autowired
	private ValidationErrorService validationErrorService;

	@PostMapping
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result) {
		ResponseEntity<?> error = validationErrorService.validate(result);
		if (Objects.nonNull(error)) {
			return error;
		}
		Wallet savedData = walletService.saveOrUpdate(wallet);
		return new ResponseEntity<Wallet>(savedData, HttpStatus.CREATED);
	}
	
	@GetMapping
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<?>  getWallets(){
		return new ResponseEntity<>(walletService.getAllData(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return new ResponseEntity<>(walletService.getById(id),HttpStatus.OK);

	}
	
	@PutMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Wallet wallet, BindingResult result) {
		ResponseEntity<?> error = validationErrorService.validate(result);
		if (Objects.nonNull(error)) {
			return error;
		}
		wallet.setId(id);
		Wallet savedData = walletService.saveOrUpdate(wallet);
		return new ResponseEntity<Wallet>(savedData, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:5173")
	public ResponseEntity<?> deleteItems(@PathVariable Long id) {
		walletService.delete(id);
		return new ResponseEntity<Wallet>(HttpStatus.OK);

	}
}
