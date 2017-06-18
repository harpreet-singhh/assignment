# assignment
Shopping Cart

Exposes set of REST endpoint's for performing various cart related operations like create cart, update cart and delete items from cart. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

JDK 8
Maven

### Building

To build the project use the below maven command

```
mvn clean install
```

## Running the tests

To run the tests use the below maven command

```
mvn test
```

## Deployment

Tomcat7 Maven Plugin enables deploying and running the project as a dynamic web application using an embedded Tomcat server.

Use the below command to deploy the project on embedded tomcat server

```
mvn tomcat7:run
```

By default the application will be published to the below URL

```
http://localhost:8080/cart-app
```

## Endpoints

Below are the REST endpoints exposed via the application.


### Create cart
Endpoint:	/rest/cart
Type:	POST
Description: Use this to insert a new item to a cart for the first time.

### Get cart
Endpoint:	/rest/cart/{cartId}
Type:	GET
Description: Returns the cart that exists with given cart id.

### Update cart
Endpoint:	/rest/cart/{cartId}
Type:	PUT
Description: Updated the cart with the given cart id with the product passed in the request.

### Delete from cart
Endpoint:	/rest/cart/{cartId}
Type:	DELETE
Description: Delete a product in the given cart id.

### Checkout cart
Endpoint:	/rest/cart/{cartId}/checkout
Type:	GET
Description: Returns the cart id with details formatted.

All the endpoint's are secured with HTTP Basic Authentication. To invoke the endpoint's use the below details
user name - admin
password - secret

## Framework's and Libraries used
* [Spring](https://projects.spring.io/spring-framework/) - DI (dependency injection), AOP , Spring Test, Spring MVC ...
* [Spring Security](https://projects.spring.io/spring-security/)
* [Jackson](http://github.com/FasterXML/jackson)
* [Hibernate Validator](http://hibernate.org/validator/)

## Built With

* [Maven](https://maven.apache.org/)