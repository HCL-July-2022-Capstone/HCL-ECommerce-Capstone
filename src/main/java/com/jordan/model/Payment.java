package com.jordan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Payment")
public class Payment {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cardNumber;
	private String paymentType;
	private int expiration;
	private int ccv;
	private String provider;
	
}
