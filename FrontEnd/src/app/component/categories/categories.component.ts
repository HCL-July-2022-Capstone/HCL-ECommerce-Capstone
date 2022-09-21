import {Component, OnInit} from '@angular/core';
import {ProductServiceService} from "../../service/product-service.service";
import {ProductModel} from "../../model/product-model.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  productModel!: ProductModel;
  data: any;

  constructor(private productService: ProductServiceService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    // access the ActivatedRoute and track the id parameter
    this.activatedRoute.paramMap.subscribe((params) => {
      this.categoryList();
    });
  }

  categoryList() {
    const cat = this.activatedRoute.snapshot.paramMap.get('cat')!;
    this.productService.getCategory(cat)
      .subscribe((product) => this.data = product);
  }
}