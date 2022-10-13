# YammieBackend
Backend API for handling restaurant orders; Spring(JS)

## General API structure
The API is divided into 3 main parts: Model, Controller and Repository. 
Model consists of the data structure of a single PlacedOrder a customer can make. 
Repository is a package that handles database, including loading it from json and handling exceptions.
the Controller integrates between that database queries and the client-server communication. 

###### Restful Request examples

> GET localhost:8080/orders
Retrieve the entire database

> GET localhost:8080/orders-by-period/{period}
Retrieve Orders made in the last period={Day, Week, Month}

> GET localhost:8080/orders/{id}
Retrieve Order with a specific id

> POST localhost:8080/orders
> Content-Type: application/json
> {"name": "Mosi", "email":"Malone@gmail", "itemList": ["Chicken"]}
Add a new order to the database

> PUT localhost:8080/orders/{id}
> Content-Type: application/json
> {"name": "Mosi", "email":"Malone@gmail", "itemList": ["Burger"]}
Edit an existing order (with a specific id)

> DELETE localhost:8080/orders/{id}
Delete a specific order

***** Delete and Edit of on order is only applied if the order was made in the last 15 minutes.


