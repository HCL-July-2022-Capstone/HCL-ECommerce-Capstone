import {Component, OnInit} from '@angular/core';
import {ProductServiceService} from 'src/app/service/product-service.service';
import {CartModel} from "../../model/cart.model";
import {CartService} from "../../service/cart.service";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  localCart: CartModel[] = [];

  // localItems!: CartModel;

  toggleNewAddress: Boolean = false;
  items: any;

  totalPrice: number = 0.00;
  totalQuantity: number = 0;

  constructor(private productService: ProductServiceService,
              private cartService: CartService) { }

  // price: any[] = [];
  //
  // subTotal = 0;
  // price = 0;

  ngOnInit(): void {
    this.getCart();
    this.updateCartStatus();
    this.listCartDetails();
  }

  getCart(): void {
    this.productService.getItems().subscribe((data: any) => {
      this.items = data;

      localStorage.setItem('localCart', JSON.stringify(this.items));
    })
  }

  // addToCart(product: ProductModel) {
  //   this.productService.addToCart(product.productId, product);
  //
  //   this.productService.getItems().subscribe((data: any) => {
  //     this.localCart = data;
  //
  //     //set item in localstorage
  //     localStorage.setItem('localCart', JSON.stringify(this.localCart));
  //
  //
  //     for(let i=0; i<this.localCart.length; i++) {
  //       console.log(i)
  //       if(this.localCart[i].productId === product.productId ) {
  //         this.localCart[i].quantity = i + 1;
  //         this.price.push({
  //           id: product.productId,
  //           n:i,
  //           qu: this.localCart[i].quantity,
  //           pr: product.productPrice,
  //           p: product.productPrice * (this.localCart[i].quantity)
  //         });
  //       }
  //       else {
  //         break;
  //       }
  //     }
  //     console.log(this.price)
  //     console.log(this.price.length)
  //   });
  //   //set item in localstorage
  //   // localStorage.setItem('price', JSON.stringify(this.price));
  // }
  //
  // addItems(localCart: CartModel) {
  //   console.log(localCart);
  //
  //   this.productService.getItems().subscribe((data: any) => {
  //     this.localCart = data;
  //     //set item in localstorage
  //     localStorage.setItem('localCart', JSON.stringify(data));
  //   })
  //   // localStorage.setItem('Cart', JSON.stringify(localCart));
  // }

  emptyCart(): void {
    this.productService.clearCart().subscribe(() => {
      alert('Cart Emptied');
    });
  }

  updateCartStatus() {
    // subscribe to the cart totalPrice
    this.cartService.totalPrice.subscribe(
      (data: any) => this.totalPrice = data
    );

    // subscribe to the cart totalQuantity
    this.cartService.totalQuantity.subscribe(
      (data: any) =>
        this.totalQuantity = data
    );
  }

  listCartDetails() {

    // get a handle to the cart items
    this.localCart = this.cartService.cartItems;

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

  incrementQuantity(theCartItem: CartModel) {
    this.cartService.addToCart(theCartItem);
    console.log(theCartItem)
    console.log(theCartItem.quantity)
  }

  decrementQuantity(theCartItem: CartModel) {
    this.cartService.decrementQuantity(theCartItem);
  }

  remove(theCartItem: CartModel) {
    this.cartService.remove(theCartItem);
  }

}
