package com.WalletApp.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WalletApp.Services.TransactionService;
import com.WalletApp.Services.ValidationErrorService;
import com.WalletApp.entities.Transaction;
import com.WalletApp.entities.Wallet;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService service;


	@Autowired
	private ValidationErrorService validationErrorService;

	@PostMapping("/{wallet_id}")
	public ResponseEntity<?> create(@PathVariable Long wallet_id, @Valid @RequestBody Transaction transaction, BindingResult result) {
		ResponseEntity<?> error = validationErrorService.validate(result);
		if (Objects.nonNull(error)) {
			return error;
		}
		Transaction transactionSavedData = service.saveOrUpdate(wallet_id , transaction);
		return new ResponseEntity<Transaction>(transactionSavedData, HttpStatus.CREATED);
	}
	
	@GetMapping("/{wallet_id}")
	public ResponseEntity<?>  getWallets(@PathVariable Long wallet_id){
		return new ResponseEntity<>(service.getAllData(wallet_id),HttpStatus.OK);
	}
	
	@GetMapping("/{wallet_id}/{id}")
	public ResponseEntity<?> getById(@PathVariable Long wallet_id, @PathVariable Long id) {
		return new ResponseEntity<>(service.getById(wallet_id,id),HttpStatus.OK);

	}
	
	@PutMapping("/{wallet_id}/{id}")
	public ResponseEntity<?> update(@PathVariable Long wallet_id,@PathVariable Long id,@Valid @RequestBody Transaction transaction, BindingResult result) {
		ResponseEntity<?> error = validationErrorService.validate(result);
		if (Objects.nonNull(error)) {
			return error;
		}
		transaction.setId(id);
		Transaction tsavedData = service.saveOrUpdate(wallet_id,transaction);
		return new ResponseEntity<Transaction>(tsavedData, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItems(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity(HttpStatus.OK);
		
}
}
