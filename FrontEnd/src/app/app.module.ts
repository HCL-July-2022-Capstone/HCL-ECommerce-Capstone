import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';

import {OKTA_CONFIG, OktaAuthModule} from '@okta/okta-angular';
import {OktaAuth} from '@okta/okta-auth-js';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ProfileComponent} from './profile/profile.component';

import {HTTP_INTERCEPTORS, HttpClientJsonpModule, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from './auth.interceptor';

import {ProductComponentComponent} from './component/product/product-component.component';
import {ProductSearchComponent} from './component/product-search/product-search.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ProductAddComponent} from './component/product-add/product-add.component';
import {NavbarComponent} from './component/navbar/navbar.component';
import {ProductDetailsComponent} from './component/product-details/product-details.component';
import {ProductsListComponent} from './component/products-list/products-list.component';
import {PagenotfoundComponent} from './pagenotfound/pagenotfound.component';
import {CategoriesComponent} from './component/categories/categories.component';
import {HomepageComponent} from './component/homepage/homepage.component';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";

import {MatInputModule} from "@angular/material/input";
import {MatButtonToggleModule} from "@angular/material/button-toggle";

import {CheckoutComponent} from './component/checkout/checkout.component';
import {AddressAddComponent} from './component/address/address-add.component';
import {AddressListComponent} from './component/address/address-list.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {StripePaymentComponent} from './component/stripe-payment/stripe-payment.component';
import {NgxScrollTopModule} from "ngx-scrolltop";
import { EditProfileComponent } from './component/edit-profile/edit-profile.component';
import {MatRadioModule} from "@angular/material/radio";

const oktaAuth = new OktaAuth({
  issuer: 'https://dev-32668171.okta.com/oauth2/default',
  clientId: '0oa6c5xmfdMgOsl3P5d7',
  redirectUri: window.location.origin + '/login/callback',
});

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    ProductComponentComponent,
    ProductSearchComponent,
    ProductAddComponent,
    NavbarComponent,
    ProductsListComponent,
    ProductDetailsComponent,
    PagenotfoundComponent,
    CategoriesComponent,
    HomepageComponent,
    CheckoutComponent,
    AddressAddComponent,
    AddressListComponent,
    StripePaymentComponent,
    EditProfileComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    OktaAuthModule,
    HttpClientModule,
    FormsModule,
    HttpClientJsonpModule,
    MatIconModule,
    MatButtonModule,
    MatRadioModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonToggleModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    NgxScrollTopModule,
    RouterModule.forRoot([
      {path: '', component: ProductComponentComponent},
      {path: 'product/:ProductModel', component: ProductComponentComponent},
      {path: 'checkout', component: CheckoutComponent},
      {path: 'addAddress', component: AddressAddComponent}
    ])
  ],

  providers: [
    {
      provide: OKTA_CONFIG,
      useValue: {oktaAuth},
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor, multi: true
    }
  ],
  bootstrap: [AppComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class AppModule {
}
