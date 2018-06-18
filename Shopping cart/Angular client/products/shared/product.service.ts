import {Injectable} from "@angular/core";
import {HttpClient, HttpParams, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product, JSONResponse} from "./product.model";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ProductService {
    private productsUrl = "http://localhost:8080/api/products";
    private orderUrl = "http://localhost:8080/api/orders";

    constructor(private httpClient: HttpClient) { }

    getProducts(): Observable<Product[]>{
        return this.httpClient.get<Array<Product>>(this.productsUrl);
    }

    createOrder(email: string): Observable<Object>{
        const params = new HttpParams().set("email", email);
        return this.httpClient.post(this.orderUrl, {"message" : email}, httpOptions);
    }

    addProductToOrder(orderId: number, productId: number, quantity: number): Observable<Object>{
        return this.httpClient.post(`${this.orderUrl}/${orderId}`, {
            "productId" : productId,
            "quantity" : quantity
        }, httpOptions);
    }

    checkoutFinalize(orderId: number, address: string): Observable<Object>{
        return this.httpClient.put(`${this.orderUrl}/${orderId}`, {
            "message:": address
        }, httpOptions);
    }

}
