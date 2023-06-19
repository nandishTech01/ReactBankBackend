package com.WalletApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Wallet {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private Long id;
	
	@NotBlank(message = "Name can't be blank")
	@Size(min = 2, max = 30)
	private String name;
	
	@Size(min = 2, max = 30)
	private String accountNumber;
	@Size(max = 100)
	private String description;
	
	@Min(1)
	@Max(3)
	private Integer priority;
	private Double currentBalance;
	
	@PrePersist
	public void setBalance(){
		this.currentBalance = new Double(0);
	}
	
	
}
