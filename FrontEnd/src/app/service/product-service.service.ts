import {Injectable} from '@angular/core';

//import
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProductModel} from '../model/product-model.model';

@Injectable({
  providedIn: 'root',
})
//Step 1 //step 2 in component
export class ProductServiceService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {} //inject

  listAllProducts(): Observable<ProductModel[]> {
    return this.http.get<ProductModel[]>(
      `${this.baseUrl}/products/getAllProducts`
    );
  }

  //add product
  addProduct(productModel: ProductModel) {
    this.http.post<ProductModel>(`${this.baseUrl}/products/addProduct`, productModel)
      .subscribe((response) => console.log(response));
  }

  //update product
  update(updateProduct: ProductModel) {
    return this.http.put<ProductModel>(`${this.baseUrl}/products/updateProduct/`,
      updateProduct)
      .subscribe((data) => console.log(data));
  }

  //get product by id
  getById(id: number): Observable<ProductModel> {
    return this.http.get<ProductModel>(`${this.baseUrl}/products/getProduct/${id}`);
  }

  //delete product
  deleteById(id: number): void {
    this.http.delete<ProductModel>(`${this.baseUrl}/products/delete/${id}`)
      .subscribe((response) => {
        console.log(response);
      });
  }

  //search
  findByName(name: any): Observable<ProductModel[]>{
    return this.http.get<ProductModel[]>(`${this.baseUrl}/products/Search/${name}`);
  }

  //get category
  getCategory(category: string): Observable<ProductModel> {
    return this.http.get<ProductModel>(`${this.baseUrl}/products/category/${category}`);
  }

  //add items to cart
  addItems(payload: any) {
    this.http.post(`${this.baseUrl}/cart/`, payload)
      .subscribe((data) => {
        console.log(data);
      });

  }

  addToCart(id: number, productModel: ProductModel): void {
    this.http
      .post<ProductModel>(`${this.baseUrl}/cart/add/${id}`, productModel)
      .subscribe((response) => {
        console.log(response);
      })
  }

  checkout() {
    this.http
      .post(`${this.baseUrl}/cart/checkout`, '')
      .subscribe((response) => {
        console.log(response);
      });
  }

  getItems() {
    return this.http.get(`${this.baseUrl}/cart/view`);
  }

  // increaseQty(payload: { productId: number; quantityOnHand: number; }) {
  //   return this.http.post(`${this.baseUrl}/cart`, payload);
  // }

  // clearCart() {
  //   return this.http.delete(`${this.baseUrl}/checkout/empty-cart`);
  // }

  // remove a single product
  // removeFromCart(id: number) {
  //   this.http.delete<ProductModel>(`${this.baseUrl}/cart/remove/${id}`)
  //     .subscribe((response) => {
  //       console.log(response);
  //     });
  // }
}
