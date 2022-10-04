import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {environment} from "../../../environments/environment";
import {PaymentInfo} from "../../common/payment-info";
import {ProductServiceService} from "../../service/product-service.service";
import {CheckoutService} from "../../service/checkout.service";
import {CartService} from "../../service/cart.service";
import {CartModel} from "../../model/cart.model";

@Component({
  selector: 'app-stripe-payment',
  templateUrl: './stripe-payment.component.html',
  // styleUrls: ['./stripe-payment.component.css']
})
export class StripePaymentComponent implements OnInit {

  checkoutFormGroup!: FormGroup;
  totalPrice: number = 0.00;
  totalQuantity: number = 0;

  // items = this.productService.getItems();

  stripe = Stripe(environment.stripePublishableKey);
  paymentInfo: PaymentInfo = new PaymentInfo();
  cardElement: any;
  displayError: any = '';

  localCart: CartModel[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductServiceService,
    private checkoutService: CheckoutService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.setupStripePaymentForm();

    this.checkoutFormGroup = this.formBuilder.group({
      creditCardDetails: this.formBuilder.group({}),
    });

    this.listCartDetails();
  }

  setupStripePaymentForm() {
    // Stripe payment handler.
    var elements = this.stripe.elements();

    // Create custom card elements with a hidden ZipCode field.
    this.cardElement = elements.create('card', { hidePostalCode: true });

    // Add card UI component into the 'card-element' <div>.
    this.cardElement.mount('#card-element');

    // Add event binding for 'change' in the 'card-element'.
    this.cardElement.on('change', (event: { complete: any; error: { message: any; }; }) => {
      // 'card-errors' element handler.
      this.displayError = document.getElementById('card-errors');

      if (event.complete) {
        this.displayError.textContent = '';
      } else if (event.error) {
        this.displayError.textContent = event.error.message;
      }
    });
  }

  get creditCardType() {
    return this.checkoutFormGroup.get('creditCardDetails.cardType');
  }

  get creditCardNameOnCard() {
    return this.checkoutFormGroup.get('creditCardDetails.nameOnCard');
  }

  get creditCardNumber() {
    return this.checkoutFormGroup.get('creditCardDetails.cardNumber');
  }

  get creditCardSecurityCode() {
    return this.checkoutFormGroup.get('creditCardDetails.securityCode');
  }

  checkout() {
    // this.productService.checkout();

    if (this.checkoutFormGroup.invalid) {
      this.checkoutFormGroup.markAllAsTouched();
      return;
    }

    // Payment info
    this.paymentInfo.amount = this.totalPrice * 100;
    this.paymentInfo.currency = 'USD';

    if (
      !this.checkoutFormGroup.invalid &&
      this.displayError.textContent === ''
    ) {
      this.checkoutService
        .createPaymentIntent(this.paymentInfo)
        .subscribe((paymentIntentResponse) => {
          this.stripe
            .confirmCardPayment(
              paymentIntentResponse.client_secret,
              {
                payment_method: {
                  card: this.cardElement
                }
              },
              { handleActions: false }
            )
            .then((result: { error: { message: any; }; }) => {
              if (result.error) {
                // show error
                alert(`An error occurred: ${result.error.message}`);
              }
            });
        });
    } else {
      this.checkoutFormGroup.markAllAsTouched();
      return;
    }
  }

  // quantity, price, and total
  listCartDetails() {

    // get a handle to the cart items
    this.localCart = this.cartService.localCart;

    // subscribe to the cart totalPrice
    this.cartService.totalPrice.subscribe(
      data => this.totalPrice = data
    );

    // subscribe to the cart totalQuantity
    this.cartService.totalQuantity.subscribe(
      data => this.totalQuantity = data
    );

    // compute cart total price and quantity
    this.cartService.computeCartTotals();
  }

}
