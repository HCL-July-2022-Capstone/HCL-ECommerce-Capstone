package com.jordan.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payment")
public class Payment {
	@Id 
	@GeneratedValue
	private int paymentId;
	private String paymentType;
	private String provider;
	private int cardNumber;
	private Date expiration;
	private int ccv;
	
	@ManyToOne
    @JoinColumn(name="userId", nullable=false)
	private User userPayment;
}
