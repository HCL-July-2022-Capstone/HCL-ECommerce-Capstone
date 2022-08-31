import {Injectable} from '@angular/core';
//import
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {ProductModel} from "../model/product-model.model";

@Injectable({
    providedIn: 'root'
})

export class ProductServiceService {

    //to invoke as parameter inside get method
    private baseUrl = "http://localhost:8080";
    httpOptions = {
        headers: new HttpHeaders(
            {'Content-Type': 'application/json'})
    };

    constructor(
        private http: HttpClient) {
    } //inject

    //Step 1 Get: list method //step 2 in component
    listAllProducts(): Observable<ProductModel[]> { //return type observables
        return this.http.get<ProductModel[]>(`${this.baseUrl}/products/getallproducts`); //returns array of products
    }

    //Step 1 Post: add method //step 2 in component
    addProduct(productModel: ProductModel): Observable<ProductModel> {
        return this.http.post<ProductModel>(`${this.baseUrl}/products/addproduct`,
            productModel, this.httpOptions).pipe(
            tap((newProduct: ProductModel) =>
                console.log(`added id=${newProduct.productId}`))
        );
    }

    //In progress: update
    updateProductById(productModel: ProductModel | undefined): Observable<any> {
        return this.http.put(`${this.baseUrl}/updateproduct`, productModel)
    }

    //In progress: search method//
    searchByProductName(term: string): Observable<ProductModel[]> {
        if (!term.trim()) {
            // if not search term, return empty hero array.
            return of([]);
        }
        return this.http.get<ProductModel[]>
        (`${this.baseUrl}/products/getproduct/?productName=${term}`);
    }

}
