<div align="center">
  <img src="./assets/images/spring-crud.png" alt="logo" width="600" />
</div>

## What is CRUD?

CRUD stands for Create, Read, Update, and Delete, which are the basic operations that can be performed on data in a database or data storage system. This concept is fundamental to database management and is often used in the context of web development, software engineering, and data management.

<div align="center">
  <img src="./assets/images/crud-diagram.png" alt="logo" width="600" />
</div>

[Source of this image](https://docs.remaketheweb.com/static/crud-diagram.png)

In this CRUD application, we are managing an employee list with fields such as firstname, lastname, and email.

## Techstack Used

![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white) ![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB) ![Vite](https://img.shields.io/badge/vite-%23646CFF.svg?style=for-the-badge&logo=vite&logoColor=white)

- **Backend:**
  - Spring - A comprehensive framework for enterprise Java development.

- **Frontend:**
  - React - A popular JavaScript library for building user interfaces.
  - Vite - A fast build tool for modern JavaScript projects.

- **Database:**
  - MySQL - A relational database management system.

## About Backend: Spring and Spring Boot

The backend of this CRUD application is built using Spring Boot, a framework that simplifies the development of Java-based applications. It follows the principles of the Spring framework and provides a variety of features to enhance productivity.

## About Frontend: React and Vite

The frontend is developed using React, a JavaScript library for building user interfaces. Vite is used as the build tool, providing fast and efficient development and production builds.

## About Database: MySQL

MySQL is employed as the relational database management system to store and manage the employee information.

## ScreenShots

<div align="center">
  <img src="./assets/images/scwa-home.png" alt="logo" width="600" />
</div>

<div align="center">
  <img src="./assets/images/scwa-list.png" alt="logo" width="600" />
</div>

<div align="center">
  <img src="./assets/images/scwa-add.png" alt="logo" width="600" />
</div>

## How to Set Up Locally

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/bishakhne0gi/Spring-Crud.git
   ```

2. **Backend Setup:**
   - Navigate to the `backend` directory.
   - Configure your MySQL database details in the `application.properties` file.
   - Run the Spring Boot application:
     ```bash
     ./mvnw spring-boot:run
     ```

3. **Frontend Setup:**
   - Navigate to the `frontend` directory.
   - Install dependencies:
     ```bash
     npm install
     ```
   - Start the development server:
     ```bash
     npm run dev
     ```

4. **Access the Application:**
   Open your browser and go to [http://localhost:3000](http://localhost:3000) to access the CRUD application.

## License

This project is licensed under the [MIT License](LICENSE).

## Conclusion

You have successfully set up the CRUD application locally. Feel free to explore, modify, and enhance the code to suit your needs. If you encounter any issues or have suggestions, please open an issue or create a pull request.