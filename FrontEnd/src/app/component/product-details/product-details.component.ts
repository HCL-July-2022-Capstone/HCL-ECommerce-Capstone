import {Component, OnInit} from '@angular/core';
import {ProductServiceService} from "../../service/product-service.service";
import {ActivatedRoute} from "@angular/router";
import {ProductModel} from "../../model/product-model.model";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  productModel!: ProductModel;

  constructor(private productService: ProductServiceService,
              private activatedRoute: ActivatedRoute,
              private snackbar: MatSnackBar) {
  }

  ngOnInit(): void {
    // access the ActivatedRoute and track the id parameter
    this.activatedRoute.paramMap.subscribe((params) => {
      this.productDetails();
    });
  }

  productDetails() {
    const id = parseInt(this.activatedRoute.snapshot.paramMap.get('id')!, 10);
    this.productService.getById(id)
      .subscribe((product) => this.productModel = product);

  }

  //addToCart
  addToCart(product: ProductModel): void {
    const id = parseInt(this.activatedRoute.snapshot.paramMap.get('id')!, 10);
    this.productService.addToCart(id, product);

    //pop up message
    this.snackbar.open(
      'Product has been added to cart!', '',
      {
        duration: 1500
      });

    // console.log(id);
    // console.log(product.productId);
  }
}
