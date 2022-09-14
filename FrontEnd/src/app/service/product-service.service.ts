
import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

//import
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProductModel} from '../model/product-model.model';

@Injectable({
  providedIn: 'root',
})
//Step 1 //step 2 in component
export class ProductServiceService {

  items: ProductModel[] = [];
  //to invoke as parameter inside get method
  private baseUrl = 'http://localhost:8080';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };


  private baseUrl = 'http://localhost:8080/products';

  constructor(private http: HttpClient) {
  }

  listAllProducts(): Observable<ProductModel[]> {
    return this.http.get<ProductModel[]>(
      `${this.baseUrl}/getAllProducts`
    );
  }

  //add product
  addProduct(productModel: ProductModel) {
    this.http.post<ProductModel>(`${this.baseUrl}/addProduct`, productModel)
      .subscribe((response) => console.log(response));
  }

  //update product
  update(updateProduct: ProductModel) {
    return this.http.put<ProductModel>(`${this.baseUrl}/updateProduct/`,
      updateProduct)
      .subscribe((data) => console.log(data));
  }

  //get product by id
  getById(id: number): Observable<ProductModel> {
    return this.http.get<ProductModel>(`${this.baseUrl}/getProduct/${id}`);
  }

  //delete product
  deleteById(id: number): void {
    this.http.delete<ProductModel>(`${this.baseUrl}/delete/${id}`)
      .subscribe((response) => {
        console.log(response);
      });
  }


  //get category
  getCategory(category: string): Observable<ProductModel> {
    return this.http.get<ProductModel>(`${this.baseUrl}/category/${category}`);

  //add items to cart

  addToCart(id: number, productModel: ProductModel): void {
    this.http
    .post<ProductModel>(`${this.baseUrl}/cart/add/${id}`, productModel)
    .subscribe((response) => {
      console.log(response);
    })
  }

  checkout(){
    this.http.post(`${this.baseUrl}/cart/checkout`,"").subscribe((response) => {
      console.log(response);
    })
  }
  getItems() {
    return this.items;
  }

  clearCart() {
    this.items = [];
    return this.items;

  }
}
