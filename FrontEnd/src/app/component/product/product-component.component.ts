import {Component, OnInit} from '@angular/core';
import {ProductServiceService} from "../../service/product-service.service";
import {ProductModel} from "../../model/product-model.model";

@Component({
    selector: 'app-product-component',
    templateUrl: './product-component.component.html',
    styleUrls: ['./product-component.component.css']
})
export class ProductComponentComponent implements OnInit {

    productModel!: ProductModel[];
    data: ProductModel | undefined;


    constructor(private productService: ProductServiceService) {
    }

    ngOnInit(): void {
        this.listAllProducts(); //only for void methods
    }

    //step 2 function: all products from service //step 1 is in service
    listAllProducts(): void {
        this.productService.listAllProducts().subscribe((productModel) => {
            this.productModel = productModel
        });
    }

    //update
    // goBack(): void {
    //     this.location.back();
    // }
    //
    // save(): void {
    //     if (this.productModel) {
    //         this.productService.updateProductById(this.data)
    //             .subscribe(() => this.goBack());
    //     }
    // }

    //step 2: function delete from service //step 1 is in service

}
