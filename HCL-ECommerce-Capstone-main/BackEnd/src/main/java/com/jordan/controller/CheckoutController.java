package com.jordan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jordan.dto.PaymentInfo;
import com.jordan.service.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
public class CheckoutController
{
	@Autowired
	private CheckoutService checkoutService;

	@PostMapping("/api/payment-intent")
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo payInfo) throws StripeException
	{
		PaymentIntent paymentIntent = checkoutService.createPaymentIntent(payInfo);

		String paymentStr = paymentIntent.toJson();

		return new ResponseEntity<>(paymentStr, HttpStatus.OK);
	}
}