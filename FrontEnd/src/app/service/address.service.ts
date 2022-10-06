import {Injectable} from '@angular/core';

//import
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AddressModel} from '../model/address.model';

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

      `${this.baseUrl}/address/getAddresses`
    );
}

  addAddress(addressModel: AddressModel){
    this.http.post<AddressModel>(`${this.baseUrl}/address/addAddress`, addressModel).subscribe((response) => console.log(response))
  }

  //add new addresses and set it. must be done before checkout
  setShippingAddress(addressModel: AddressModel) {
    this.http
      .post<AddressModel>(`${this.baseUrl}/address/setShippingAddress`, addressModel)
      .subscribe((response) => console.log(response));
  }

  setBillingAddress(addressModel: AddressModel) {
    this.http
      .post<AddressModel>(`${this.baseUrl}/address/setBillingAddress`, addressModel)
      .subscribe((response) => console.log(response));
  }

  deleteAddress(addressModel: AddressModel){
    this.http.post<AddressModel>(`${this.baseUrl}/address/delete`, addressModel).subscribe((response) => console.log(response));
  }

}
