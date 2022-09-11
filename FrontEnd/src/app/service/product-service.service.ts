import { Injectable } from '@angular/core';
//import
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, of, tap } from 'rxjs';
import { ProductModel } from '../model/product-model.model';

@Injectable({
  providedIn: 'root',
})
export class ProductServiceService {
  //to invoke as parameter inside get method
  private baseUrl = 'http://localhost:8080';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient) {} //inject

  //Step 1 Get: list method //step 2 in component
  listAllProducts(): Observable<ProductModel[]> {
    //return type observables
    return this.http.get<ProductModel[]>(
      `${this.baseUrl}/products/getAllProducts`
    ); //returns array of products
  }

  //Step 1 Post: add method //step 2 in component
  addProduct(productModel: ProductModel) {
    this.http
      .post<ProductModel>(`${this.baseUrl}/products/addProduct`, productModel)
      .subscribe((response) => console.log(response));
  }

  //In progress: update
  updateProductById(productModel: ProductModel | undefined): Observable<any> {
    return this.http.put(`${this.baseUrl}/updateproduct`, productModel);
  }

  //In progress: search method//
  searchByProductName(term: string): Observable<ProductModel[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<ProductModel[]>(
      `${this.baseUrl}/products/getproduct/?productName=${term}`
    );
  }
  //delete
  deleteById(id: number): void {
    this.http
      .delete<ProductModel>(`${this.baseUrl}/products/delete/${id}`)
      .subscribe((response) => {
        console.log(response);
      });
  }

  addToCart(id: number, productModel: ProductModel): void {
    this.http
    .post<ProductModel>(`${this.baseUrl}/cart/add/${id}`, productModel)
    .subscribe((response) => {
      console.log(response);
    })
  }
}
