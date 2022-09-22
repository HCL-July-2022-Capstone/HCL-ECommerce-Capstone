
import { Component, OnInit } from '@angular/core';
import { AddressModel } from 'src/app/model/address.model';
import { AddressService } from 'src/app/service/address.service';
import { ProductModel } from '../../model/product-model.model';
import { ProductServiceService } from '../../service/product-service.service';

@Component({
  selector: 'app-address-add',
  templateUrl: './address-add.component.html',
  styleUrls: ['./address.component.css'] //newly added
})
export class AddressAddComponent implements OnInit {
  addressModel: AddressModel[] = [];
  //for adding
  newShippingAddress: AddressModel = {
    id:0,
    street: '',
    city: '',
    state: '',
    zipcode: '',
    country: '',
    username: '',
  };

  added = false;

  constructor(private addressService: AddressService) {}

  ngOnInit(): void {}

  addAddress(): void {
    const data = {
      id: this.newShippingAddress.id,
      street: this.newShippingAddress.street,
      city: this.newShippingAddress.city,
      state: this.newShippingAddress.state,
      zipcode: this.newShippingAddress.zipcode,
      country: this.newShippingAddress.country,
      username: this.newShippingAddress.username
    };

    console.log(data);
    this.addressService.addAddress(data);
  }



}

