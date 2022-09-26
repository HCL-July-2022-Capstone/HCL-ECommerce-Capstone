import {Component, OnInit} from '@angular/core';
import {AddressModel} from 'src/app/model/address.model';
import {AddressService} from 'src/app/service/address.service';

@Component({
  selector: 'app-address-add',
  templateUrl: './address-add.component.html'
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
  newBillingAddress: AddressModel = {
    id: 0,
    street: '',
    city: '',
    state: '',
    zipcode: '',
    country: '',
    username: ''
  };

  added = false;

  constructor(private addressService: AddressService) {}

  ngOnInit(): void {}

  // //step 2: function add product from service //step 1 is in service
  addShippingAddress(): void {
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
    this.addressService.addShippingAddress(data);
  }
  addBillingAddress(): void {
    const data = {
      id: this.newBillingAddress.id,
      street: this.newBillingAddress.street,
      city: this.newBillingAddress.city,
      state: this.newBillingAddress.state,
      zipcode: this.newBillingAddress.zipcode,
      country: this.newBillingAddress.country,
      username: this.newBillingAddress.username
    };

    console.log(data);
    this.addressService.addBillingAddress(data);
  }

  addAddresses(): void {
    this.addShippingAddress();
    this.addBillingAddress();
  }

  sameShippingAndBilling(): void{
    this.newBillingAddress = this.newShippingAddress;
  }
}

