import {Component, Input, OnInit} from '@angular/core';

import {ProductServiceService} from '../../service/product-service.service';
import {ProductModel} from '../../model/product-model.model';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-product-component',
  templateUrl: './product-component.component.html',
  styleUrls: ['./product-component.component.css'],
})
//step 2 function: all products from service //step 1 is in service
export class ProductComponentComponent implements OnInit {

  @Input() currentProduct: ProductModel = {
    categoryName: '',
    image: '',
    productDescription: '',
    productId: 0,
    productName: '',
    productPrice: 0,
    quantityOnHand: 0,
  };

  productModel!: ProductModel[];
  data: ProductModel | undefined;


  constructor(private productService: ProductServiceService,
              private snackbar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.listAllProducts(); //only for void methods
  }

  //get all
  listAllProducts(): void {
    this.productService.listAllProducts().subscribe((productModel) => {
      this.productModel = productModel;
    });
  }

  //delete
  deleteProductById(product: ProductModel): void {
    this.productModel = this.productModel
      .filter((data) => data !== product);
    this.productService.deleteById(product.productId);

    this.snackbar.open(
      'Product has been deleted!', '',
      {
        duration: 1500
      });
  }

  // save update
  save(): void {
    //body
    const data = {
      productId: this.currentProduct.productId,
      productName: this.currentProduct.productName,
      productDescription: this.currentProduct.productDescription,
      productPrice: this.currentProduct.productPrice,
      categoryName: this.currentProduct.categoryName,
      quantityOnHand: this.currentProduct.quantityOnHand,
      image: this.currentProduct.image,
    };

    console.log(data);
    this.productService.update(data);

    this.snackbar.open(
      'Product list has been updated!', '',
      {
        duration: 1500
      });
  }

  //addToCart
  addToCart(product: ProductModel): void {
    this.productService.addToCart(product.productId, product);

    this.snackbar.open(
      'Product has been added to cart!', '',
      {
        duration: 1500
      });
  }

}

