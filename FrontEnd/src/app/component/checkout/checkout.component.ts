import {Component, OnInit} from '@angular/core';
import {ProductServiceService} from 'src/app/service/product-service.service';
import {CartModel} from "../../model/cart.model";
import {CartService} from "../../service/cart.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ProductModel} from "../../model/product-model.model";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  localCart: CartModel[] = [];
  productModel!: ProductModel;
  totalPrice: number = 0.00;
  totalQuantity: number = 0;

  constructor(private productService: ProductServiceService,
              private cartService: CartService,
              private snackbar: MatSnackBar) {  }

  ngOnInit(): void {
    this.listCartDetails();
  }

  incQTY(cart: CartModel) {
    // increase quantity in ca
    this.cartService.addToCart(cart);
  }

  // updateCartStatus() {
  //   // subscribe to the cart totalPrice
  //   this.cartService.totalPrice.subscribe(
  //     (data: any) => this.totalPrice = data
  //   );
  //
  //   // subscribe to the cart totalQuantity
  //   this.cartService.totalQuantity.subscribe(
  //     (data: any) =>
  //       this.totalQuantity = data
  //   );
  // }

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

  decQTY(cart: CartModel) {
    this.cartService.decQTY(cart);
  }

  removeItem(cart: CartModel) {
    this.cartService.remove(cart);

    //popup message
    this.snackbar.open(
      'Product has been removed from the cart!', '',
      {
        duration: 1500
      });
  }
}
