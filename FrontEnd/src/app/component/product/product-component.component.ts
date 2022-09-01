import {Component, Input, OnInit} from '@angular/core';
import {ProductServiceService} from '../../service/product-service.service';
import {ProductModel} from '../../model/product-model.model';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-component',
  templateUrl: './product-component.component.html',
  styleUrls: ['./product-component.component.css'],
})
export class ProductComponentComponent implements OnInit {

  // @Input() viewMode = false;

  @Input() currentProduct: ProductModel = {
    categoryName: "",
    image: "",
    productDescription: "",
    productId: 0,
    productName: "",
    productPrice: 0,
    quantityOnHand: 0
  }

  productModel!: ProductModel[];
  data: ProductModel | undefined;

  constructor(
    private productService: ProductServiceService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.listAllProducts(); //only for void methods
  }

  //step 2 function: all products from service //step 1 is in service
  listAllProducts(): void {
    this.productService.listAllProducts().subscribe((productModel) => {
      this.productModel = productModel;
    });
  }

  //get by id
  getById(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.productService.getById(id)
      .subscribe(data => this.productModel = data)
  }

  //delete
  deleteProductById(product: ProductModel): void {
    this.productModel = this.productModel.filter((data) => data !== product);
    this.productService.deleteById(product.productId);
  }

  //update
  // goBack(): void {
  //     this.location.reload();
  // }

  save(): void {
    const data = {
      productId: this.currentProduct.productId,
      productName: this.currentProduct.productName,
      productDescription: this.currentProduct.productDescription,
      productPrice: this.currentProduct.productPrice,
      categoryName: this.currentProduct.categoryName,
      quantityOnHand: this.currentProduct.quantityOnHand,
      image: this.currentProduct.image
    }

    // if(this.data) {
      console.log(data);
      this.productService.update(data);
    // }
  }

  //step 2: function delete from service //step 1 is in service
}
