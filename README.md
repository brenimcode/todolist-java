## TodoList Java Project

Welcome to the TodoList Java project! This repository showcases a robust Java **back-end** application built using **Spring Boot, Maven, and integrated with an H2 Database Engine** for development. This project emphasizes secure user authentication, RESTful API development, and effective exception handling. This project was developed as part of a comprehensive course to demonstrate the application of industry-standard practices and tools.

<br>

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![Maven](https://img.shields.io/badge/ApacheMaven-C71A36?logo=apachemaven&logoColor=white&style=for-the-badge)

---

#### Project Overview

The TodoList Java project provides essential functionalities for managing tasks with user authentication and authorization capabilities. It ensures efficient task management through REST APIs, supporting creation, updating, and retrieval operations. The application is structured to maintain data integrity and security, essential for enterprise-grade applications.

---

#### Key Features

1. **User Management**
   - **UserModel:** Centralizes user data management with secure password hashing and user-specific attributes.
   - **UserController:** Implements RESTful endpoints for user creation and authentication.
   - **IUserRepository:** JPA repository ensuring efficient data access and persistence.

2. **Task Management**
   - **TaskModel:** Represents tasks with comprehensive attributes including description, title, priority, and date fields.
   - **TaskController:** Provides REST APIs for task CRUD operations, ensuring data consistency and user-specific access controls.
   - **ITaskRepository:** JPA repository for seamless task data management and retrieval.

3. **Security and Exception Handling**
   - **FilterTaskAuth:** Implements basic authentication mechanisms for secure API access.
   - **ExceptionHandlerController:** Centralizes exception handling to provide meaningful error responses and maintain application stability.

4. **Integration and Deployment**
   - **Spring Boot and Maven:** Industry-standard tools for rapid development, dependency management, and build automation.
   - **H2 Database Engine:** In-memory database for development and testing, scalable for production environments with external database configurations.
   - **Cloud Deployment:** Scalable deployment options on cloud platforms like AWS, Azure, or Heroku, ensuring high availability and performance.

---

#### Getting Started

To run the project locally:

1. **Clone the Repository:**
   ```sh
   git clone https://github.com/brenimcode/todolist-java.git
   cd todolist-java
   ```

2. **Build and Run the Application:**
   ```sh
   mvn clean package
   java -jar target/todolist-java.jar
   ```

3. **Accessing APIs:**
   - **Users:** `http://localhost:8080/users/`
     - POST: Create a new user.
   - **Tasks:** `http://localhost:8080/tasks/`
     - POST: Create a new task.
     - GET: Retrieve tasks for the authenticated user.
     - PUT: Update a task by ID.

---

#### Deployment Considerations

For production deployment:
- Configure a persistent database (e.g., MySQL, PostgreSQL) for data durability and scalability.
- Implement secure network configurations and authentication mechanisms to protect user and application data.
- Utilize cloud platforms for scalable deployment, ensuring high availability and reliability for enterprise applications.

---

#### Developed During a Course

This project was developed as part of a structured course designed to teach the application of industry-standard practices and tools in Java back-end development. It demonstrates a practical application of concepts such as secure user authentication, RESTful API development, exception handling, and deployment considerations.

---

Feel free to explore the [repository](https://github.com/brenimcode/todolist-java) and contribute to its development.

---

By following these practices and showcasing the development during a course, you demonstrate a commitment to learning and applying best practices, which is highly valued by large companies and experienced developers.
