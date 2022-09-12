import {Component, OnInit} from '@angular/core';
import {ProductServiceService} from "../../service/product-service.service";
import {ActivatedRoute} from "@angular/router";
import {ProductModel} from "../../model/product-model.model";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  productModel!: ProductModel;

  constructor(private productService: ProductServiceService,
              private activatedRoute: ActivatedRoute) {
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
}
