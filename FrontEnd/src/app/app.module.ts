import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

//imported
import { HttpClientModule} from "@angular/common/http";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductComponentComponent } from './component/product/product-component.component';
import { ProductSearchComponent } from './component/product-search/product-search.component';
import {FormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import { ProductAddComponent } from './component/product-add/product-add.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductComponentComponent,
    ProductSearchComponent,
    ProductAddComponent //imported
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        MatInputModule,
        MatButtonToggleModule,
//imported
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
