package com.jordan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "orders", schema = "inventory")
public class Orders {
	@Id
	@GeneratedValue
	private int orderId;
	private String orderNumber;
	private float totalPrice;
	private int totalQuantity;
	private String orderStatus;

	// one to many unidirectional mapping
	// default fetch type for OneToMany: LAZY
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "orderNumber", referencedColumnName = "orderId")
//	private List<Product> product;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User userOrder;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shippingAddressId", referencedColumnName = "addressId")
	private Address shippingAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billingAddressId", referencedColumnName = "addressId")
	private Address billingAddress;

}
