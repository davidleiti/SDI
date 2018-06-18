# Shopping Cart

	
	
Write an application that simulates a shopping cart. The following entities will be used:
* BaseEntity\<ID\> (id: ID)
* Product extends BaseEntity\<Long\> (name: String, price: Integer, stock: Integer, lineltems: SetsLineltem>)
* Lineltem (product: Product, order. Order, quantity: Integer)
* Order extends BaseEntity\<Long\> (dete: Date, totalPrice: Integer, contactinfo: Contactinfo, status: Enum, lineltems: Set\<Lineltem\>)
* Contactinfo(email: String, address: String)
* Constraints: Product.name is unique; Order.date is unique, Order.date may be of any Date/Time API type such that both date and
time are recorded

When visiting the address http:/localhost:4200/store/products the following elements will be shown:
* A Cart: \<no-of-items\> link or button showing the number of items in the cart
* A list of products in the following format: \<product-name\> - \<price\> - \<add-to-cart-button\>
When clicking on the add-to-cart button the product is added to the cart and <no-of-items> is incremented

When accessing the 'cart link/button' the following elements are shown:
* The Cart: \<no-of-items\>
* A list of selected products in the following format: \<product-name\> \<quantity-textfield\>
* An Email label and an email-textfield
* An Address label and an address-textfield
* A checkout button

The quantity-textfields are editable and will initially display information according to the user selection in the previous screen.
The \<no-of-items\> will always show the sum of the elements in the quantity-textfields.
When leaving the email textfield a request is made to the backend and an new order (with all the filled in information) is added to the db with the status CART.
When clicking the checkout-button a request is made to the backend and:
* The address is added to the db
* The status is changed to SUBMITTED
