import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {ProductsComponent} from "./products/products.component";
import { CartComponent } from './products/cart/cart.component';
import { ProductService} from './products/shared/product.service';

@NgModule({
  declarations: [
    AppComponent,
    CartComponent,
    ProductsComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
