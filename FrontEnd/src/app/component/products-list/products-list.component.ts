import {Component, OnInit} from '@angular/core';
import {ProductModel} from "../../model/product-model.model";
import {ProductServiceService} from "../../service/product-service.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {

  productModel!: ProductModel[];

  constructor(private productService: ProductServiceService,
              private snackbar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.listAllProducts();
  }

  listAllProducts(): void {
    this.productService.listAllProducts()
      .subscribe((products) => {
        this.productModel = products
      })
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
