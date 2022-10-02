import {Component, EventEmitter, OnInit} from '@angular/core';
import {ProductServiceService} from "../../service/product-service.service";
import {ActivatedRoute} from "@angular/router";
import {ProductModel} from "../../model/product-model.model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {CartModel} from "../../model/cart.model";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  productModel!: ProductModel;

  total = 0;
  priceList!: any[];
  items!: any[];

  newQuantity = new EventEmitter<number>();
  cartModel: CartModel[] = [];

  constructor(private productService: ProductServiceService,
              private activatedRoute: ActivatedRoute,
              private  snackbar: MatSnackBar) {}

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
  addToCart(product: ProductModel) {

    this.productService.addToCart(product.productId, product);

    this.snackbar.open(
      'Product has been added to cart!', '',
      {
        duration: 1500
      });
  }
}
