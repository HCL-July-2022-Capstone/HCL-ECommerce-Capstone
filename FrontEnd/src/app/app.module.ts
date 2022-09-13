import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {RouterModule} from '@angular/router';

import { OktaAuthModule, OKTA_CONFIG } from '@okta/okta-angular';
import { OktaAuth } from '@okta/okta-auth-js';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './auth.interceptor';


import { ProductComponentComponent } from './component/product/product-component.component';
import { ProductSearchComponent } from './component/product-search/product-search.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import { ProductAddComponent } from './component/product-add/product-add.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { CheckoutComponent } from './component/checkout/checkout.component';
import { AddressAddComponent } from './component/address/address-add.component';


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
    CheckoutComponent,
    AddressAddComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    OktaAuthModule,
    HttpClientModule,
    FormsModule,
    MatInputModule,
    MatButtonToggleModule,
    ReactiveFormsModule,
        RouterModule.forRoot([
          { path: '', component: ProductComponentComponent },
          { path: 'product/:ProductModel', component: ProductComponentComponent },
          { path: 'checkout', component: CheckoutComponent },
          { path: 'addAddress', component: AddressAddComponent}
        ])
    ],

  providers: [
    {
      provide: OKTA_CONFIG,
      useValue: { oktaAuth },
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor, multi: true
    },
  ],

  bootstrap: [AppComponent],

  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
})
export class AppModule {}