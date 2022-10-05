import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductModel } from '../../model/product-model.model';
import { ProductServiceService } from '../../service/product-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css'],
})

export class ProductAddComponent implements OnInit {
  productModel: ProductModel[] = [];
  //for adding
  newProduct: ProductModel = {
    productId: 0,
    productName: '',
    productDescription: '',
    productPrice: 0,
    quantityOnHand: 0,
    categoryName: '',
    image: '',
  };
  added = false;

  update = false;

  constructor(
    private productService: ProductServiceService,
    private route: ActivatedRoute,
    private snackbar: MatSnackBar
  ) {}

  ngOnInit(): void {
    var id = this.route.snapshot.params['id'];

    if (id != null) {
      this.productService
        .getById(id)
        .subscribe(
          (response) => (
            (this.newProduct = response),
            console.log(response),
            (this.update = true)
          )
        );
    }
  }

  //step 2: function add product from service //step 1 is in service
  saveProduct(): void {
    const data = {
      productId: this.newProduct.productId,
      productName: this.newProduct.productName,
      productDescription: this.newProduct.productDescription,
      productPrice: this.newProduct.productPrice,
      quantityOnHand: this.newProduct.quantityOnHand,
      categoryName: this.newProduct.categoryName,
      image: this.newProduct.image,
    };

    console.log(data);
    this.productService.addProduct(data);

    //pop up message
    this.snackbar.open('Product list has been updated!', '', {
      duration: 1500,
    });
  }

  myfunc() {
    location.replace('http://localhost:4200/getAllProducts');
  }
}
