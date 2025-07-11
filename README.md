# 🐟 FishBlogWeb

## 📌 Project Description

**FishBlogWeb** is a fullstack web application developed using **Spring Boot** and **Thymeleaf**, backed by a **MySQL** database. The system allows users to manage, create, edit, and delete blog posts related to fish species. It includes role-based access, where Admin users have full management capabilities over the system.

## 🧰 Technologies Used

- ✅ Spring Boot (Java 17+)
- ✅ Thymeleaf (dynamic HTML templating)
- ✅ Spring Data JPA (data access layer)
- ✅ MySQL (relational database)
- ✅ Bootstrap 5 (responsive UI framework)
- ✅ Lombok (to reduce boilerplate Java code)

## 🗄️ Project Structure

-  fishblogweb/
-  ├── src/
-  │   ├── main/
-  │   │   ├── java/com/tnluan/fishblogweb/
-  │   │   │   ├── config/            # Web & security configurations
-  │   │   │   ├── controller/        # Controllers (handling web requests)
-  │   │   │   │    ├── admin/
-  │   │   │   │    ├── user/
-  │   │   │   ├── dto/  
-  │   │   │   ├── entity/            # JPA Entities (data models)
-  │   │   │   ├── mapper/ 
-  │   │   │   ├── exception/ 
-  │   │   │   ├── interceptor/ 
-  │   │   │   ├── repository/        # Spring Data Repositories
-  │   │   │   ├── service/           # Business logic layer
-  │   │   │   ├── util/ 
-  │   │   │   └── FishBlogWebApplication.java
-  │   │   └── resources/
-  │   │       ├── static/            # Static assets (CSS, JS, images)
-  │   │       ├── templates/         # Thymeleaf HTML templates
-  │   │       │    ├── admin/
-  │   │       │    ├── user/
-  │   │       │    ├── errors/
-  │   │       └── application.properties

## 🔐 Default Admin Account

Upon first run, the application automatically creates a default Admin account:

- **Username:** `ADMIN`
- **Password:** `ADMIN`

> ⚠️ It is highly recommended to change the password after the first login for security purposes.

## 🚀 How to Run the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/NguyenLuan11/FishBlogWeb.git
   cd fishblogweb
2. Open the project using an IDE like **IntelliJ IDEA** or **Eclipse**.
3. Ensure that **MySQL** is installed, running, and a database named `fishblog_db` (or your preferred name) has been created.
4. Open the `src/main/resources/application.properties` file and configure your database connection:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/fishblog_db
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   spring.jpa.hibernate.ddl-auto=update
5. Run the project using:
  - The FishBlogWebApplication.java file, or
  - The command: ./mvnw spring-boot:run
6. Access the application in your browser at:
   http://localhost:8080
   
## 📧 Contact
- Author: Nguyen Luan
- Email: nguyenluan0101020@gmail.com
