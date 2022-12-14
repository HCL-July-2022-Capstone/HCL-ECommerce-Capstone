import {Component, OnInit} from '@angular/core';
import {ProductServiceService} from '../../service/product-service.service';
import {ProductModel} from '../../model/product-model.model';
import {ActivatedRoute} from '@angular/router';
import {MatSnackBar} from "@angular/material/snack-bar";
import {CartService} from "../../service/cart.service";
import {CartModel} from "../../model/cart.model";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  data: any;
  items: any[] = [];

  constructor(
    private productService: ProductServiceService,
    private activatedRoute: ActivatedRoute,
    private snackbar: MatSnackBar,
    private cartService: CartService
  ) {
  }

  ngOnInit(): void {

    // access the ActivatedRoute and track the id parameter
    this.activatedRoute.paramMap.subscribe(() => {
      this.categoryList();
    });

    this.productService.listAllProducts();
  }

  categoryList() {
    const cat = this.activatedRoute.snapshot.paramMap.get('cat')!;
    this.productService.getCategory(cat)
      .subscribe((product) => {
        this.data = product
      });
  }


  addToCart(product: ProductModel) {

    this.productService.addToCart(product.productId, product);
    console.log(product.productId);


    const theCartItem = new CartModel(product);
    this.cartService.addToCart(theCartItem);

    this.snackbar.open(
      'Product has been added to cart!', '',
      {
        duration: 1500
      });
  }
}
