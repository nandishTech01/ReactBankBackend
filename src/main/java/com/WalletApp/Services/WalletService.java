package com.WalletApp.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WalletApp.Exception.WalletException;
import com.WalletApp.entities.Wallet;
import com.WalletApp.repositories.WalletRepository;

@Service
public class WalletService {

	@Autowired
	private WalletRepository walletRepository;
	
	
	public  Wallet saveOrUpdate(Wallet wallet) {
		if(wallet.getId()==null) {
			walletRepository.save(wallet);
		}
		else{
			walletRepository.save(wallet);
		}
		 return wallet;
	}
	
		public boolean delete(Long id) {
			Optional<Wallet> wallet = walletRepository.findById(id);
			if(wallet.isPresent()) {
				walletRepository.delete(wallet.get());
				return true ;
			}
			throw new WalletException("Wallet with id : " + id +"  "+"does not exist.");
		}
	
		public List<Wallet> getAllData(){
			return walletRepository.findAllByOrderByPriority();
		}
		
		public Wallet getById(Long id) {
			Optional<Wallet> wallet = walletRepository.findById(id);
			if(wallet.isPresent()) {
				return wallet.get();
			}
			throw new WalletException("Wallet with id : " + id +"  "+"does not exist.");
		}
	
}
