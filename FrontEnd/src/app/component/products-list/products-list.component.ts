import {Component, OnInit} from '@angular/core';
import {ProductModel} from "../../model/product-model.model";
import {ProductServiceService} from "../../service/product-service.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CartModel} from "../../model/cart.model";
import {CartService} from "../../service/cart.service";

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {

  productModel!: ProductModel[];

  // product: ProductModel = new class implements ProductModel {
  //   categoryName: string;
  //   image: string;
  //   productDescription: string;
  //   productId: number;
  //   productName: string;
  //   productPrice: number;
  //   quantityOnHand: number;
  // }();
  // newProduct!: ProductModel;
  // items: CartModel[] = [];
  // product: ProductsListModel = new ProductsListModel();

  // newProduct: ProductModel = {
  //   productId: 0,
  //   productName: '',
  //   productDescription: '',
  //   productPrice: 0,
  //   quantityOnHand: 0,
  //   categoryName: '',
  //   image: '',
  // };

  constructor(private productService: ProductServiceService,
              private  snackbar: MatSnackBar,
              private cartService: CartService) {

    // this.product.productId = product.productId;
    // this.product.productName = product.productName;
    // this.product.image = product.image;
    // this.product.productPrice = product.productPrice;
  }

  ngOnInit(): void {
    this.listAllProducts();
  }

  listAllProducts(): void {
    this.productService.listAllProducts()
      .subscribe((products) => {
        this.productModel = products
      });
  }

  //addToCart
  // addToCart(product: ProductModel): void {
  //   this.productService.addToCart(product.productId, product);
  //
  //   this.snackbar.open(
  //     'Product has been added to cart!', '',
  //     {
  //       duration: 1500
  //     });
  // }

  // addToCart() {
  //
    // const product = {
    //   productId: this.newProduct.productId,
    //   productName: this.newProduct.productName,
    //   productDescription: this.newProduct.productDescription,
    //   productPrice: this.newProduct.productPrice,
    //   quantityOnHand: this.newProduct.quantityOnHand,
    //   categoryName: this.newProduct.categoryName,
    //   image: this.newProduct.image,
    // };
  //
  //   const theCartItem = new CartModel(product);
  //   this.cartService.addToCart(theCartItem);
  //
  //   console.log(product);
  //   console.log(theCartItem);
  //
  //   this.snackbar.open(
  //     'Product has been added to cart!', '',
  //     {
  //       duration: 1500
  //     });
  // }
  //
  // incQTY(cart: CartModel) {
  //   // increase quantity in cart by adding
  //   this.cartService.addToCart(cart);
  // }

  addToCart(product: ProductModel) {

    const cart = new CartModel(product);
    this.cartService.addToCart(cart);

    this.snackbar.open(
      'Product has been added to cart!', '',
      {
        duration: 1500
      });
  }
}
