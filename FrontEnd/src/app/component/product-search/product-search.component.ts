import { Component, OnInit } from '@angular/core';
import { ProductModel } from "../../model/product-model.model";
import { ProductServiceService } from "../../service/product-service.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {

  products?: ProductModel[];

  currentProduct: ProductModel = {
    productId: 0,
    productName: '',
    productDescription: '',
    productPrice: 0,
    quantityOnHand: 0,
    categoryName: '',
    image: ''
  };

  currentIndex = -1;
  searchTerms = "";

  constructor(private productService: ProductServiceService,
              private snackbar: MatSnackBar) { }

  ngOnInit(): void {
    this.listAllProducts();
  }

  listAllProducts(): void {
    this.productService.listAllProducts()
      .subscribe({
        next: (data) => {
          this.products = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      })
  }

  searchProductName(): void {
    this.currentProduct = {
      productId: 0,
      productName: '',
      productDescription: '',
      productPrice: 0,
      quantityOnHand: 0,
      categoryName: '',
      image: ''
    }

    this.currentIndex = -1;

    this.productService.findByName(this.searchTerms)
      .subscribe({
        next: (data) => {
          console.log(this.searchTerms);
          this.products = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  retrieveProducts(): void {
    this.productService.listAllProducts()
      .subscribe({
        next: (data) => {
          this.products = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveProducts();
    this.currentProduct = {
      productId: 0,
      productName: '',
      productDescription: '',
      productPrice: 0,
      quantityOnHand: 0,
      categoryName: '',
      image: ''
    };
    this.currentIndex = -1;

  };

  setActiveProduct(product: ProductModel, index: number): void {
    this.currentProduct = product;
    this.currentIndex = index;
  }

  //addToCart
  addToCart(product: ProductModel): void {
    this.productService.addToCart(product.productId, product);

    //pop up message
    this.snackbar.open(
      'Product has been added to cart!', '',
      {
        duration: 1500
      });
    // console.log(product.productId);
  }
}
