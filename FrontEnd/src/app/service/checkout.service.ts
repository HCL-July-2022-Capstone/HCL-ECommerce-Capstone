import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PaymentInfo } from '../common/payment-info';

@Injectable({
  providedIn: 'root',
})
export class CheckoutService {
  baseURL = 'http://localhost:8080';
  private paymentIntentUrl = this.baseURL + '/api/payment-intent';

  constructor(private httpClient: HttpClient) {}

  createPaymentIntent(paymentInfo: PaymentInfo): Observable<any> {
    return this.httpClient.post<PaymentInfo>(
      this.paymentIntentUrl,
      paymentInfo
    );
  }
}
