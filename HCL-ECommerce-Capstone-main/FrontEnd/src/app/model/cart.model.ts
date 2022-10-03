import {ProductModel} from "./product-model.model";

export class CartModel {
  productId: number;
  productName: string;
  image: string;
  productPrice: number;
  quantity: number;
  totalPrice: number;

  // cart items
  constructor(product: ProductModel) {
    this.productId = product.productId;
    this.productName = product.productName;
    this.image = product.image;
    this.productPrice = product.productPrice;
    this.quantity = 1;
    this.totalPrice = this.quantity * product.productPrice;
  }
}
