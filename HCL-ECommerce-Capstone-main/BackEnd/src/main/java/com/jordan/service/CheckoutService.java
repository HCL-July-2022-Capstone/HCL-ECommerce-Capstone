package com.jordan.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import com.jordan.dto.PaymentInfo;

@Service
public class CheckoutService
{
	@Value("${stripe.key.secret}")
	String secretKey;

	public PaymentIntent createPaymentIntent(PaymentInfo payInfo) throws StripeException
	{
		Stripe.apiKey = secretKey;

		List<String> paymentMethod = new ArrayList<>();
		paymentMethod.add("card");

		Map<String, Object> params = new HashMap<>();
		params.put("amount", payInfo.getAmount());
		params.put("currency", payInfo.getCurrency());
		params.put("payment_method_types", paymentMethod);

		return PaymentIntent.create(params);
	}
}