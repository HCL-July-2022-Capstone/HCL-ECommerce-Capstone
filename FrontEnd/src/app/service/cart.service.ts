import { Injectable } from '@angular/core';

import { BehaviorSubject, Subject } from 'rxjs';
import {CartModel} from "../model/cart.model";

@Injectable({
  providedIn: 'root'
})

export class CartService {

  localCart: CartModel[] = [];

  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

  storage: Storage = localStorage;

  constructor() {

    // read data from storage
    let data = JSON.parse(this.storage.getItem('localCart') as string);

    if (data != null) {
      this.localCart = data;
      console.log(this.localCart);

      // compute totals based on the data that is read from storage
      this.computeCartTotals();
    }
  }

  addToCart(cart: CartModel) {
    let alreadyExistsInCart: boolean = false;
    let existingCartItem = undefined;
    // check if we already have the item in our cart
    if (this.localCart.length > 0) {
      // find the item in the cart based on item id
      existingCartItem =  this.localCart.find(tempCartItem => tempCartItem.productId === cart.productId);

      alreadyExistsInCart = (existingCartItem != undefined);

      console.log(existingCartItem)
    }

    if (alreadyExistsInCart) {
      // increment the quantity
      cart.quantity++;
    }
    else {
      // just add the item to the array
      this.localCart.push(cart);
    }

    // compute cart total price and total quantity
    this.computeCartTotals();
  }

  computeCartTotals() {

    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for (let currentCartItem of this.localCart) {
      totalPriceValue += currentCartItem.quantity * currentCartItem.productPrice;
      totalQuantityValue += currentCartItem.quantity;
    }

    // publish the new values ... all subscribers will receive the new data
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);

    // persist cart data
    this.persistCartItems();
  }

  persistCartItems() {
    this.storage.setItem('localCart', JSON.stringify(this.localCart));
  }

  decQTY(cart: CartModel) {

    cart.quantity--;

    if (cart.quantity === 0) {
      this.remove(cart);
    }
    else {
      this.computeCartTotals();
    }
  }

  remove(cart: CartModel) {

    // get index of item in the array
    const itemIndex = this.localCart.findIndex(tempCartItem =>
      tempCartItem.productId === cart.productId);

    // if found, remove the item from the array at the given index
    if (itemIndex > -1) {
      this.localCart.splice(itemIndex, 1);

      this.computeCartTotals();
    }
  }

}
