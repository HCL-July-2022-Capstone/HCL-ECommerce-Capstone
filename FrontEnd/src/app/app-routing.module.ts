import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductComponentComponent} from "./component/product/product-component.component";
import {ProductAddComponent} from "./component/product-add/product-add.component";

const routes: Routes = [
    {path:'', redirectTo: 'products/getallproducts', pathMatch:'full'},
    {path:'getallproducts', component: ProductComponentComponent },
    {path:'updateproduct', component: ProductComponentComponent},
    {path:'addproduct', component: ProductAddComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
