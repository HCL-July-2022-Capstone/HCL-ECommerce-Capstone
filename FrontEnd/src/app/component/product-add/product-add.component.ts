import { Component, OnInit } from '@angular/core';
import {ProductModel} from "../../model/product-model.model";
import {ProductServiceService} from "../../service/product-service.service";

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {

    productModel: ProductModel[]=[];
    //for adding
    newProduct: ProductModel = {
        productId: 0,
        productName: '',
        productDescription: '',
        productPrice: 0,
        quantityOnHand: 0,
        categoryName: '',
        categoryId: 0
    };
    added = false;

    constructor(private productService: ProductServiceService) { }

    ngOnInit(): void {
    }

    // //step 2: function add product from service //step 1 is in service
    saveProduct(): void {
        const data = {
            productId: this.newProduct.productId,
            productName: this.newProduct.productName,
            productDescription: this.newProduct.productDescription,
            productPrice: this.newProduct.productPrice,
            quantityOnHand: this.newProduct.quantityOnHand,
            categoryName: this.newProduct.categoryName,
            categoryId: this.newProduct.categoryId
        };

        // addProduct(productId: number, productName: string, productDescription: string,
        //            productPrice: number, quantityOnHand: number, categoryName: string,
        //            categoryId: number)
        // {
            this.productService.addProduct(data)
            .subscribe((productModel) => {
            // console.log(any);
                this.productModel.push(this.newProduct);
                this.added = true;
            // return this.productService.addProduct({
            //     productId, productName,
            //     productDescription, productPrice, quantityOnHand, categoryName, categoryId
            // } as ProductModel)
            //     .subscribe((productModel) => {
            //         this.productModel.push(productModel) //post methods need to be pushed
            //     });
        });
    }

    newProductModel(): void {
        this.added = false;
        this.newProduct = {
            productId: 0,
            productName: '',
            productDescription: '',
            productPrice: 0,
            quantityOnHand: 0,
            categoryName: '',
            categoryId: 0
        }
    }
}
