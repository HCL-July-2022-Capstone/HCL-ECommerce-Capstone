import {Component, OnInit} from '@angular/core';
import {ProductServiceService} from 'src/app/service/product-service.service';
import {CartModel} from "../../model/cart.model";
import {BehaviorSubject, Subject} from "rxjs";
import {ProductModel} from "../../model/product-model.model";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  localCart: CartModel[] = [];
  // newCart: CartModel = {
  //   image: "",
  //   productId: 0, productName: "", productPrice: 0, quantity: 0, totalPrice: 0
  //
  // };
  // localItems!: CartModel;
  // productModel!: ProductModel[];
  // product!: ProductModel;
  // cart: any;
  toggleNewAddress: Boolean = false;
  items: any;

  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

  constructor(private productService: ProductServiceService) {

    // let data = JSON.plocalStorage.getItem('localCart');
    //
    // if (data != null) {
    //   this.localCart = data;
    // }
  }

  subTotal = 0;
  price = 0;

  ngOnInit(): void {
    this.getCart();
  }

  getCart(): void {
    this.productService.getItems().subscribe((data: any) => {
      this.items = data;

      localStorage.setItem('localCart', JSON.stringify(this.items));
    })
  }

  // addItem(cart: CartModel) {
  //
  //   // this.productService.addToCart(this.items.productId, this.items);
  //
  //   // check if we already have the item in our cart
  //   let alreadyExistsInCart: boolean = false;
  //
  //   if (this.localCart.length > 0) {
  //     // find the item in the cart based on item id
  //     this.localCart.find( tempCartItem =>
  //       tempCartItem.productId === cart.productId);
  //
  //     // check if we found it
  //     alreadyExistsInCart = true
  //   }
  //   if (alreadyExistsInCart) {
  //     // increment the quantity
  //     cart.quantity += 1;
  //   }
  //   else {
  //     // just add the item to the array
  //     this.localCart.push(cart);
  //   }
  //   // compute cart total price and total quantity
  //
  // }

  addToCart(product: ProductModel) {
    this.productService.addToCart(product.productId, product);

    // const dataCart = {
    //   productId: this.newCart.productId,
    //   productName: this.newCart.productName,
    //   image: this.newCart.image,
    //   productPrice: this.newCart.productPrice,
    //   quantity: 1,
    //   totalPrice: this.newCart.quantity * this.newCart.productPrice,
    // }

    this.productService.getItems().subscribe((data: any) => {
      this.localCart = data;

      //set item in localstorage
      localStorage.setItem('localCart', JSON.stringify(this.localCart));

      // @ts-ignore
    //   let cartData = JSON.parse(localStorage.getItem('localCart'));
    //   if (cartData == null) {
    //     let storeData: any = [];
    //     storeData.push(this.localCart)
    //     localStorage.setItem('Cart', JSON.stringify(storeData));
    //   } else {
    //     var id = product.productId;
    //     let index: number = -1;
    //
    //     // @ts-ignore
    //     this.items = JSON.parse(localStorage.getItem('localCart'));
    //
    //     for (let i = 0; i < this.items.length; i++) {
    //       if (id === parseInt(this.items[i].productId)) {
    //         this.items[i].quantity = this.localItems.quantity;
    //         index = i;
    //         break;
    //       }
    //     }
    //   if (index == -1) {
    //     this.items.push(this.localCart);
    //     localStorage.setItem('localCart', JSON.stringify(this.localCart))
    //
    //   }
    // }
    });
  }

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

  // increase quantity
  incQTY(){
  }

  decQTY(items: CartModel[]) {
    for (let item of items) {
      if (item.quantity != 1) {
        item.quantity -= 1;
      }
    }
  }

  // totalPrice(quantity: number, price: number) {
  //   this.total = quantity * price;
  //
  //   this.items.forEach((item: any) => {
  //     this.subTotal += (quantity * price)
  //   })
  // }

  emptyCart(): void {
    this.productService.clearCart().subscribe(() => {
      this.getCart();
      alert('Cart Emptied');
    });
  }

  checkout(): void {
    this.productService.checkout();
  }

}
