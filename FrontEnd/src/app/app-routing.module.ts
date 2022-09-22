import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { OktaAuthGuard, OktaCallbackComponent } from '@okta/okta-angular';
import { ProfileComponent } from './profile/profile.component';



import { ProductComponentComponent } from './component/product/product-component.component';
import { ProductAddComponent } from './component/product-add/product-add.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { ProductsListComponent } from './component/products-list/products-list.component';
import { ProductDetailsComponent } from './component/product-details/product-details.component';
import { ProductSearchComponent } from './component/product-search/product-search.component';
import { CategoriesComponent } from './component/categories/categories.component';
import { HomepageComponent } from './component/homepage/homepage.component';

import { CheckoutComponent } from './component/checkout/checkout.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full',
    component: HomepageComponent,
  }, //homepage
  {
    path: 'login/callback',
    component: OktaCallbackComponent,
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
    {path:'addproduct', component: ProductAddComponent},
    {path:'Search', component: ProductSearchComponent}
  ,
  {
    path: 'products/:id',
    component: ProductDetailsComponent,
  },
  {
    path: 'category/:cat',
    component: CategoriesComponent,
  },
  /*{
    path: 'search/:term',
    component: ProductSearchComponent,
  },*/
  {
    path: 'addProduct',
    component: ProductAddComponent,
  },
  {
    path: 'update/:id',
    component: ProductAddComponent,
  },
  {
    path: 'getAllProducts',
    component: ProductComponentComponent, //table
  },
  {
    path: 'products',
    component: ProductsListComponent, //grid list
  },

  {path: 'checkout', component: CheckoutComponent},
  {
    path: '**',
    component: PagenotfoundComponent, //Wild Card Route for 404 request
  },


    

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
