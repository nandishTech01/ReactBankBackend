package com.WalletApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WalletApp.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long>{

}
