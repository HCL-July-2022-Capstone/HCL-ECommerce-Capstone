import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../model/user-model.model";
import {AddressModel} from "../model/address-model.model";

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  private baseUrl = 'http://localhost:8080/user';

  constructor(private http: HttpClient) { }

  //get all
  getAllUsers(): Observable<UserModel[]> {
    return this.http.get<UserModel[]>(`${this.baseUrl}/getallusers`);
  }

  //add user
  addUser(userModel: UserModel) {
    this.http
      .post<UserModel[]>(`${this.baseUrl}/join`, userModel)
      .subscribe((response) => console.log(response));
  }

  //add new address
  addAdress(addressModel: AddressModel) {
    this.http
      .post<AddressModel[]>(`${this.baseUrl}/addAddress`, addressModel)
      .subscribe((response) => console.log(response));
  }

  //get by id
  getUserId(id: number): Observable<UserModel> {
    return this.http.get<UserModel>(`${this.baseUrl}/get/${id}`);
  }

  //update
  update(userModel: UserModel){
    return this.http.put(`${this.baseUrl}/update/${userModel.id}`,
        userModel);
  }

  //delete
  deleteUser(id: number): void {
    this.http.delete<UserModel>(`${this.baseUrl}/get/${id}`)
      .subscribe((response) => {
        console.log(response);
      });
  }
}
