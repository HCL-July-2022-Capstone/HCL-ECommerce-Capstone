import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from 'src/app/service/product-service.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
})
export class CheckoutComponent implements OnInit {
  carts: any;
  cartDetails;
  toggleNewAddress: Boolean = false;
  constructor(private productService: ProductServiceService) {}

  ngOnInit(): void {
    this.getCart();
  }
  getCart(): void {
    this.productService.getItems().subscribe((data: any) => {
      this.carts = data;
      // this.cartDetails = data.data;

      console.log(this.carts);
    });
  }
  incQTY(id, quantityOnHand): void {
    const payload = {
      productId: id,
      quantityOnHand,
    };
    this.productService.increaseQty(payload).subscribe(() => {
      this.getCart();
      alert('Product Added');
    });
  }
  emptyCart(): void {
    this.productService.clearCart().subscribe(() => {
      this.getCart();
      alert('Cart Emptied');
    });
  }

  checkout(): void {
    this.productService.checkout();
  }
}
