
import { Component, OnInit } from '@angular/core';
import { ProductModel } from '../../model/product-model.model';
import { ProductServiceService } from '../../service/product-service.service';

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

  constructor(private productService: ProductServiceService) {}

  ngOnInit(): void {}

  // //step 2: function add product from service //step 1 is in service
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
  }
}

