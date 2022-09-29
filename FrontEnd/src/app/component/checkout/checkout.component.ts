import {Component, Input, OnInit} from '@angular/core';
import {ProductServiceService} from 'src/app/service/product-service.service';
import {ProductModel} from "../../model/product-model.model";
import {CartModel} from "../../model/cart.model";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

//items = this.productService.getItems();
  //toggleNewAddress: Boolean = false;

  //stripe = Stripe(environment.stripePublishableKey);
  //paymentInfo: PaymentInfo = new PaymentInfo();
  //cardElement: any;
  //displayError: any = '';

  cartModel: CartModel[] = [];
  productModel!: ProductModel[];
  product!: ProductModel;
  toggleNewAddress: Boolean = false;
  items!: CartModel[];

  @Input()
  quantity: number = 0;
  total: number = 0;

  priceList:any[] = [];

  constructor(private productService: ProductServiceService) {
  }

  subTotal = 0;
  price = 0;

  ngOnInit(): void {
    this.getCart();
  }

  getCart(): void {
    this.productService.getItems().subscribe((data: any) => {
      this.items = data;
      console.log(data);
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

  // remove from cart
  removeItem(cart: CartModel) {
    this.cartModel = this.cartModel
      .filter((data) => data !== cart);
    this.productService.removeFromCart(cart.productId);

    //remove from database
    this.removeFromDatabase(this.product);
  }

  incQTY(items: CartModel[]) {
    for (let item of items)  {
      item.quantity = item.quantity + 1;
    }
    // const payload = {
    //   productId: id,
    //   quantityOnHand,
    // };
    // this.productService.increaseQty(payload).subscribe(() => {
    //   this.getCart();
    // });
  }

  totalPrice(quantity: number, price: number) {
   this.total = quantity * price;

    this.items.forEach((item: any) => {
      this.subTotal += (quantity * price)
    })
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

    removeFromDatabase(product: ProductModel): void {
      this.productModel = this.productModel.filter((data) =>
        data !== product);
      this.productService.removeFromCart(product.productId);
    }
}
