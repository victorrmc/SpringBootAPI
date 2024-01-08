# Spring Boot API for Product Management

This project represents the server-side of a product management application, designed to work seamlessly with the client-side developed in React.
The entire project comprises two main parts, with the client-side repository available at [this link](https://github.com/victorrmc/ClienteReact).

## Description

The server-side of this project is implemented as a RESTful API using Spring Boot. The database used is H2, an in-memory database that provides fast and efficient data storage.

Authentication with SpringBoot in the application is performed using the token provided by the Spring Boot API. Additionally, a product CRUD (Create, Read, Update, Delete) is implemented, allowing operations on products by making requests to different API endpoints.

Within this repository, you will find a detailed user manual in the `UserManual` folder, guiding you through the functionalities and proper usage of the application.

## Unit Testing 
The project includes unit tests implemented in JUnit to ensure the robustness and reliability of the codebase

## Additional Resources
- Class Diagram: Check the `ClassDiagram` folder for `ClassDiagram.svg`, which is a class diagram.
- Role Diagram: Find the `RoleDiagram.svg` in the `ClassDiagram` folder, illustrating the role diagram.
- Postman Collection: Explore the `postman` folder for an exported Postman collection containing various API endpoints.
- SQL Queries: Refer to the `querySQL` folder for two SQL queries.


## Installation

Follow these steps to set up and run the server-side locally:

1. Clone this repository.
2. Build and run the application
   ```bash
   git clone git@github.com:victorrmc/SpringBootAPI.git
   cd SpringBootAPI
   ```
3. The server will be accessible at http://localhost:8080.
## API Endpoints

### Product Management

- GET /products/get: Retrieve a list of all products.
- GET /products/find/{id}: Retrieve details of a specific product.
- POST /products/create: Create a new product.
- DELETE /products/delete/{id}: Delete a product.
- PUT /products/edit: Update an existing product.
- PUT /products/desactivate/{id}: Deactivate a product.

### Price Reductions

- GET /priceReductions/get: Retrieve a list of all price reductions.
- POST /priceReductions/create: Create a new price reduction.
- DELETE /priceReductions/delete/{id}: Delete a price reduction.
- PUT /priceReductions/edit: Update an existing price reduction.

### Suppliers

- GET /suppliers/get: Retrieve a list of all suppliers.
- POST /suppliers/create: Create a new supplier.
- DELETE /suppliers/delete/{id}: Delete a supplier.
- PUT /suppliers/edit: Update an existing supplier.

### Authentication

- POST /auth/login: User login.
- POST /auth/register: User registration.

### Users

- GET /users/get: Retrieve a list of all users.
- POST /users/create: Create a new user.
- DELETE /users/delete/{id}: Delete a user.

## Database
The H2 in-memory database is utilized for this project. You can access the H2 console at http://localhost:8080/h2-console when the application is running. JDBC URL is jdbc:h2:mem:testdb, and credentials are typically set to default values.
