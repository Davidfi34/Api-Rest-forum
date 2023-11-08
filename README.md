# RESTful API for Alura Course Forum

## Introduction
The Alura Course Forum is a magical space where students of the Alura platform can post their questions regarding the available courses. This place is filled with learning opportunities and collaboration among students, instructors, and moderators.

## Technology Stack
Our API is built using the following technologies:

- **Java 17:** We leverage the power of Java 17 to build a robust backend.
- **Spring Boot 3:** We rely on Spring Boot 3 to create a flexible and efficient application.
- **MySQL:** Our database of choice for data storage.
- **Authentication:** For authentication, we use the `java-jwt` dependency from Auth0 (version 4.2.0).
- **Documentation:** To document our API, we employ the Swagger UI platform. https://springdoc.org/
- **Swagger-link:** https://api-forum-d92g.onrender.com/swagger-ui/index.html

![imagen](https://github.com/Davidfi34/Api-Rest-forum/assets/46968835/8bf52096-6328-43ad-973d-e8c6cbbb8bad)




## Features
Our API will focus on "topics" and provide the following functionalities to users:

1. **Create a new topic:** Users can post new questions or discussions related to Alura courses.

2. **Display all created topics:** All existing topics in the course forum can be viewed.

3. **View a specific topic:** Access to a specific topic to view its details and responses.

4. **Update a topic:** Users can modify and update their own topics related to courses.

5. **Delete a topic:** In case it is no longer needed, topics related to courses can be deleted.

## CRUD and REST Architecture
Our goal is to provide a comprehensive set of CRUD operations (Create, Read, Update, Delete) for managing topics related to Alura courses. These operations will adhere to the best practices of the REST (Representational State Transfer) architectural model.

## Summary
At the end of our development in this phase, we will have a fully functional RESTful API with the following features:

- Implementation of routes following REST best practices.
- Data validations according to business rules.
- Utilization of a database for information persistence.


