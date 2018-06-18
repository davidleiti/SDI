import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Product, JSONResponse} from "../shared/product.model";
import {ProductService} from "../shared/product.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

    private products: Array<Product>;
    private showingCart: boolean;
    private noOfItems: number;
    private cartProducts: Array<Product>;
    private address: string;
    private email: string;
    private orderCreated: boolean;
    private orderId: number;

  constructor(private service: ProductService,
              private router: Router) {  }

  ngOnInit() {
    this.noOfItems = 0;
    this.showingCart = false;
    this.loadProducts();
    this.cartProducts = [];
    this.address = "";
  }

  // Load the initial list of products from the database, initialize quantities to 0
  loadProducts(): void{
      this.service.getProducts()
        .subscribe(products => {
            this.products = products;
            for (let i = 0; i < this.products.length; i++)
                this.products[i].quantity = 0;
        });

  }

  goToCart(): void{
      this.showingCart = true;
  }

  // Add a new product to the cart or increase its quantity if already added
  addProduct(product): void {
      let check = false;
      for (let i = 0; i < this.cartProducts.length; i++){
          if (this.cartProducts[i].id == product.id){
              this.cartProducts[i].quantity += 1;
              check = true;
          }
      }
      if (!check){
          product.quantity += 1;
          this.cartProducts.push(product);
      }
      this.refreshNoOfItems();
  }


  // Set the quantity of a product to the one specified in the input
  setValue(event){
      for (let i = 0; i < this.cartProducts.length; i++){
          if (this.cartProducts[i].id == event.target.name)
              this.cartProducts[i].quantity = parseInt(event.target.value);
      }
      this.refreshNoOfItems();
  }

  // Recompute number of items based on quantities of all products in the cart
  refreshNoOfItems(){
      let sum = 0;
      for (let i = 0; i < this.cartProducts.length; i++)
        sum += this.cartProducts[i].quantity;
      this.noOfItems = sum;
  }

  // Event handler for address input text change
  onAddressChange(event){
      this.address = event.target.value;
      console.log(this.address);
  }

  // Event handler for email input text change
  // If first change, send a post request to the server to create a new order
  onEmailChange(event){
      if (this.orderCreated){
          this.email = event.target.value;
      }
      else{
        this.orderCreated = true;
        this.email = event.target.value;
        this.service.createOrder(this.email).subscribe(<JSONResponse>(response) => {
            this.orderId = parseInt(response.message);
            console.log(this.orderId);});
      }
  }

  // Event handler for checkout button pressed, sends post requests for products and a put request to modify order status to SUBMITTED
  onCheckout(){
      for (let i = 0; i < this.cartProducts.length; i++){
          this.service.addProductToOrder(this.orderId, this.cartProducts[i].id, this.cartProducts[i].quantity).subscribe(<JSONResponse>(response) => {
             console.log(response);
          });
      }
      this.service.checkoutFinalize(this.orderId, this.address).subscribe(<JSONResponse>(response) => {console.log(response)});
  }

}
