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

  constructor(private productService: ProductServiceService,
              private snackbar: MatSnackBar,
              private cartService: CartService) {  }

  ngOnInit(): void {
    this.listAllProducts();
  }

  listAllProducts(): void {
    this.productService.listAllProducts()
      .subscribe((products) => {
        this.productModel = products
      });
  }

  //add To Cart
  // addToCart(product: ProductModel): void {
  //   this.productService.addToCart(product.productId, product);
  //
  //   this.snackbar.open(
  //     'Product has been added to cart!', '',
  //     {
  //       duration: 1500
  //     });
  // }


  addToCart(product: ProductModel) {

    const cart = new CartModel(product);
    this.cartService.addToCart(cart);

    this.productService.addToCart(product.productId, product);
    console.log(product.productId);

    this.snackbar.open(
      'Product has been added to cart!', '',
      {
        duration: 1500
      })
  }
}
