import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { OktaAuthGuard, OktaCallbackComponent } from '@okta/okta-angular';
import { ProfileComponent } from './profile/profile.component';

import {ProductComponentComponent} from "./component/product/product-component.component";
import {ProductAddComponent} from "./component/product-add/product-add.component";

const routes: Routes = [
  { path: 'login/callback',
    component: OktaCallbackComponent
  },
  {
    path: 'protected',
    loadChildren: () =>
      import('./protected/protected.module').then((m) => m.ProtectedModule),
    canActivate: [OktaAuthGuard],
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [OktaAuthGuard],
  },
    {path:'', redirectTo: 'products/getallproducts', pathMatch:'full'},
    {path:'getallproducts', component: ProductComponentComponent },
    {path:'updateproduct', component: ProductComponentComponent},
    {path:'addproduct', component: ProductAddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }