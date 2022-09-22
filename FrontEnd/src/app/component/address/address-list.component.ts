<<<<<<< HEAD
import {Component, EventEmitter, OnInit, Output} from '@angular/core';
=======
import {Component, EventEmitter, NgModule, OnInit, Output} from '@angular/core';
import { NgxScrollTopModule } from 'ngx-scrolltop';
>>>>>>> parent of 3f49945 (Search, Styling)
import { AddressModel } from 'src/app/model/address.model';
import { AddressService } from 'src/app/service/address.service';
import { CheckoutComponent } from '../checkout/checkout.component';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html'
})


export class AddressListComponent implements OnInit {

  addressModel!: AddressModel[];
  display = false;

  @Output() onAddNewAddress = new EventEmitter<boolean>();
  constructor(private addressService: AddressService) {
  }

  ngOnInit(): void {
    this.listAllAddresses();
  }

  listAllAddresses(): void {
    this.addressService.getAddresses()
      .subscribe((addresses) => {
        this.addressModel = addresses
      });
  }

  addNewAddress(): void{
    this.display = true;
  }

  setShippingAddress(address: AddressModel): void{
    this.addressService.setShippingAddress(address);
  }

  setBillingAddress(address: AddressModel): void{
    this.addressService.setBillingAddress(address);
  }

  deleteAddress(address: AddressModel): void{
    this.addressService.deleteAddress(address);
  }

}
