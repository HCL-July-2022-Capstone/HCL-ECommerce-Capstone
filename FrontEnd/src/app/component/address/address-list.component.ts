import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import { AddressModel } from 'src/app/model/address.model';
import { AddressService } from 'src/app/service/address.service';

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
