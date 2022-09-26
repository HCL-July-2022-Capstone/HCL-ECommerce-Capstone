import {Component, OnInit} from '@angular/core';
import {ProductServiceService} from 'src/app/service/product-service.service';
import {ProductModel} from "../../model/product-model.model";
import {CartModel} from "../../model/cart.model";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  cartModel: CartModel[] = [];
  // cartModel = [];
  productModel: ProductModel[] = [];
  // items: any;
  product!: ProductModel;
  // cartDetails: any;
  toggleNewAddress: Boolean = false;
  items: any;
  carts: any;
  private totalItems: any;

  constructor(private productService: ProductServiceService) {
  }

  subTotal = 0;

  ngOnInit(): void {
    this.getCart();
    this.cartTotal();
  }

  getCart(): void {
    this.productService.getItems().subscribe((data: any) => {
      this.items = data;
      console.log(data);

      //   data.push({
      //     image: this.items.image,
      //   // productId
      //   //   :
      //   //   undefined
      //   // productName
      //   //   :
      //   //   undefined
      //   // productPrice
      //   //   :
      //   //   undefined
      //   // quantity
      //   //   :
      //   //   1
      //   // totalPrice
      //   //   :
      //   //   NaN
      // })
      //   ;
      // this.cartModel.push({
      //         productId: data.productId,
      //         productName: data.productName,
      //         image: data.image,
      //         productPrice: data.productPrice,
      //         quantity: 1,
      //         totalPrice: this.items.quantity * data.productPrice
      //       });
      //     console.log(this.cartModel)
      //
      // if (this.cartModel.length === 0) { // empty cart
      //   // create an array
      //   this.cartModel.push({
      //     productId: data.productId,
      //     productName: data.productName,
      //     image: data.image,
      //     productPrice: data.productPrice,
      //     quantity: 1,
      //     totalPrice: this.items.quantity * data.productPrice
      //   });
      // } else {
      //   // loop when cart is not empty
      //   for (let i in this.cartModel) {
      //     // increment quantity if a product is repeated
      //     if (this.cartModel[i].productId === data.productId) {
      //       this.cartModel[i].quantity++;
      //       break;
      //     } else {
      //       // create an array
      //       this.cartModel.push({
      //         productId: data.productId,
      //         productName: data.productName,
      //         image: data.image,
      //         productPrice: data.productPrice,
      //         quantity: 1,
      //         totalPrice: this.items.quantity * data.productPrice
      //       });
      //     }
      //   }
      //   console.log(this.cartModel)
      // }
      //
      // // calculate subtotal
      // this.subTotal = 0;
      // this.items.forEach((item: any) => {
      //   this.subTotal += (item.quantity * item.productPrice)
      // })

      //pop up message
      // this.snackbar.open(
      //   'Product has been added to the cart!', '',
      //   {
      //     duration: 1500
      //   });

      // this.carts.push(data);
      // this.cartDetails = data.data;
      // console.log(this.cartModel);
      // console.log(this.cartTotal());
    });

    // this.cartModel.push({
    //   productId: this.items.productId,
    //   productName: this.items.productName,
    //   image: this.items.image,
    //   productPrice: this.items.productPrice,
    //   quantity: 1,
    //   totalPrice: this.items.quantity * this.items.productPrice
    // });
    // console.log(this.cartModel);

  }

  updateCartItems(quantity: number) {
    this.items.next(quantity);
  }

  // addToCartModel(data: ProductModel): void {
  //   // this.productService.addToCart(product);
  //
  //   if (this.cartModel.length === 0) { // empty cart
  //     // create an array
  //     this.cartModel.push({
  //       productId: data.productId,
  //       productName: data.productName,
  //       image: data.image,
  //       productPrice: data.productPrice,
  //       quantity: 1,
  //       totalPrice: this.items.quantity * data.productPrice
  //     });
  //   } else {
  //     // loop when cart is not empty
  //     for (let i in this.cartModel) {
  //       // increment quantity if a product is repeated
  //       if (this.cartModel[i].productId === data.productId) {
  //         this.cartModel[i].quantity++;
  //       } else {
  //         // create an array
  //         this.cartModel.push({
  //           productId: data.productId,
  //           productName: data.productName,
  //           image: data.image,
  //           productPrice: data.productPrice,
  //           quantity: 1,
  //           totalPrice: this.items.quantity * data.productPrice
  //         });
  //       }
  //     }
  //   }
  //
  //   // calculate subtotal
  //   this.items.forEach((item: any) => {
  //     this.subTotal += (item.quantity * item.productPrice)
  //   })
  //
  //   //pop up message
  //   // this.snackbar.open(
  //   //   'Product has been added to the cart!', '',
  //   //   {
  //   //     duration: 1500
  //   //   });
  // }

  incQTY(id: number, quantityOnHand: number): void {
    const payload = {
      productId: id,
      quantityOnHand,
    };
    this.productService.increaseQty(payload).subscribe(() => {
      this.getCart();
      alert('Product Added');
    });
  }

  // total price of all items in the cart
  cartTotal() {
    this.items.forEach((item: any) => {
      this.subTotal += (item.quantity * item.productPrice)
    })
    console.log(this.subTotal);
  }

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
