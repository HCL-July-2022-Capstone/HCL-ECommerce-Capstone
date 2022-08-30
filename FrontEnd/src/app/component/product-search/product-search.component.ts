import { Component, OnInit } from '@angular/core';
import {ProductModel} from "../../model/product-model.model";
import {debounceTime, distinctUntilChanged, Observable, Subject, switchMap} from "rxjs";
import {ProductServiceService} from "../../service/product-service.service";

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {

  productModel$!: Observable<ProductModel[]>;
  private searchTerms = new Subject<string>();

  constructor(private productService: ProductServiceService) { }

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.productModel$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),
      // ignore new term if same as previous term
      distinctUntilChanged(),
      // switch to new search observable each time the term changes
      switchMap((term: string) => this.productService.searchByProductName(term)),
    );
  }

}
