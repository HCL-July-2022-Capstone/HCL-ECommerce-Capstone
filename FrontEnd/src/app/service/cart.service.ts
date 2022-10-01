import { Injectable } from '@angular/core';

import { BehaviorSubject, Subject } from 'rxjs';
import {CartModel} from "../model/cart.model";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems: CartModel[] = [];

  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

  storage: Storage = sessionStorage;

  constructor() {

    // read data from storage
    let data = JSON.parse(this.storage.getItem('cartItems') as string);

    if (data != null) {
      this.cartItems = data;

      // compute totals based on the data that is read from storage
      this.computeCartTotals();
    }

  }

  addToCart(theCartItem: CartModel) {
    let alreadyExistsInCart: boolean = false;
    let existingCartItem = undefined;
    // check if we already have the item in our cart
    if (this.cartItems.length > 0) {
      // find the item in the cart based on item id
      existingCartItem =  this.cartItems.find( tempCartItem => tempCartItem.productId === theCartItem.productId);

      alreadyExistsInCart = (existingCartItem != undefined);

      console.log(existingCartItem)
    }

    if (alreadyExistsInCart) {
      // increment the quantity
      theCartItem.quantity++;
    }
    else {
      // just add the item to the array
      this.cartItems.push(theCartItem);
    }

    // compute cart total price and total quantity
    this.computeCartTotals();
  }

  computeCartTotals() {

    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;
    let cartItemQuantity: number = 0;

    for (let currentCartItem of this.cartItems) {
      totalPriceValue += currentCartItem.quantity * currentCartItem.productPrice;
      totalQuantityValue += currentCartItem.quantity;
    }


    // publish the new values ... all subscribers will receive the new data
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);

    // log cart data just for debugging purposes
    this.logCartData(totalPriceValue, totalQuantityValue);

    // persist cart data
    this.persistCartItems();
  }

  persistCartItems() {
    this.storage.setItem('cartItems', JSON.stringify(this.cartItems));
  }

  logCartData(totalPriceValue: number, totalQuantityValue: number) {

    console.log('Contents of the cart');
    for (let tempCartItem of this.cartItems) {
      const subTotalPrice = tempCartItem.quantity * tempCartItem.productPrice;
      console.log(`name: ${tempCartItem.productName}, quantity=${tempCartItem.quantity},
      productPrice=${tempCartItem.productPrice}, subTotalPrice=${subTotalPrice}`);
    }

    console.log(`totalPrice: ${totalPriceValue.toFixed(2)}, totalQuantity: ${totalQuantityValue}`);
    console.log('----');
  }

  decrementQuantity(theCartItem: CartModel) {

    theCartItem.quantity--;

    if (theCartItem.quantity === 0) {
      this.remove(theCartItem);
    }
    else {
      this.computeCartTotals();
    }
  }

  remove(theCartItem: CartModel) {

    // get index of item in the array
    const itemIndex = this.cartItems.findIndex( tempCartItem => tempCartItem.productId === theCartItem.productId );

    // if found, remove the item from the array at the given index
    if (itemIndex > -1) {
      this.cartItems.splice(itemIndex, 1);

      this.computeCartTotals();
    }
  }

}
