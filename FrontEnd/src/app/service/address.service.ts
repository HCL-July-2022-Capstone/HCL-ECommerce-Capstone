import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

//import
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, of, tap } from 'rxjs';
import { AddressModel } from '../model/address.model';

@Injectable({
  providedIn: 'root',
})
export class AddressService {
  items: AddressModel[] = [];
  //to invoke as parameter inside get method
  private baseUrl = 'http://localhost:8080';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  constructor(private http: HttpClient) {} //inject

 
  getAddresses(): Observable<AddressModel[]> {
    return this.http.get<AddressModel[]>(
      `${this.baseUrl}/cart/getAddresses`
    ); 
  }

  //add new addresses and set it. must be done before checkout
  addShippingAddress(addressModel: AddressModel) {
    this.http
      .post<AddressModel>(`${this.baseUrl}/cart/setShippingAddress`, addressModel)
      .subscribe((response) => console.log(response));
  }

  addBillingAddress(addressModel: AddressModel) {
    this.http
      .post<AddressModel>(`${this.baseUrl}/cart/setBillingAddress`, addressModel)
      .subscribe((response) => console.log(response));
  }

}
