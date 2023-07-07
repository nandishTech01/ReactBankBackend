package com.WalletApp.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WalletApp.Exception.WalletException;
import com.WalletApp.entities.Transaction;
import com.WalletApp.entities.Wallet;
import com.WalletApp.repositories.TransactionRepository;
import com.WalletApp.repositories.WalletRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private WalletRepository walletRepository;

	public Transaction saveOrUpdate(Long walletId, Transaction transaction) {
		Optional<Wallet> wallet = walletRepository.findById(walletId);
		if (wallet.isPresent()) {
			transaction.setWallet(wallet.get());
			transactionRepository.save(transaction);
			return transaction;
		}
		return null;
	}

	public boolean delete(Long id) {
		Optional<Transaction> transaction = transactionRepository.findById(id);
		if (transaction.isPresent()) {
			transactionRepository.delete(transaction.get());
			return true;
		}
		throw new WalletException("Wallet with id : " + id + "  " + "does not exist.");
	}

	public List<Transaction> getAllData(Long walletId) {
		Optional<Wallet> wallet = walletRepository.findById(walletId);
		if (wallet.isPresent()) {
			return transactionRepository.findByWallet(wallet.get());
		}
		return null;
	}

	public Transaction getById(Long wallet_id,Long id) {
	Optional<Wallet> wallet = walletRepository.findById(wallet_id);
		if (wallet.isPresent()) {
			Optional<Transaction> transaction = transactionRepository.findById(id);
			if(transaction.isPresent()) {
				return transaction.get();
			}
		}
		throw new WalletException("Wallet with id : " + id + "  " + "does not exist.");
	}
}
