
# YashAirways-Reservation-System-With-Jwt-Authentication-And-complete-Backend-Services-


# ✈️ YashAirways Reservation System

Welcome to the **YashAirways Reservation System**, a full-fledged backend application built with **Java Spring Boot** that simulates a real-world airline ticket booking and reservation system. It supports **user and admin roles**, **JWT authentication**, **PDF ticket generation**, **email notifications**, and much more.

🔗 Live API Base URL: [https://yashairways-reservation-system-w-production.up.railway.app/check].

---

## 🚀 Features

- 🔐 Secure **JWT-based Authentication**
- 👥 Role-based access: `admin` and `user`
- 📩 Signup/Login with **Email Notification**
- 🧾 **PDF Ticket Generation** with airline-style formatting
- 📬 **Email ticket delivery**
- 🗑️ Daily automatic cleanup of expired tickets
- 🧭 RESTful APIs for complete airline booking flow
- 🧾 Admin-only APIs for managing flights, schedules, and users
- 🐘 MongoDB integration for data persistence
- 📦 Deployed for free using **Railway App**

---

## 🛠️ Tech Stack

| Layer        | Tech                             |
|--------------|----------------------------------|
| Language     | Java 17                          |
| Framework    | Spring Boot                      |
| Database     | MongoDB + MongoDB Atlas (Cloud)  |
| Auth         | Spring Security + JWT            |
| PDF Utility  | iText PDF                        |
| Mail         | JavaMailSender                   |
| Deployment   | Railway (Free Hosting)           |
| Build Tool   | Maven                            |
| IDE          | IntelliJ IDEA                    |

---


🔒 JWT Authentication Flow
User/Admin logs in via /yashairways/login

JWT Token is returned and used for further requests

Protected routes:

/admin/** → requires admin role

/user/** → requires user role

🧾 PDF Ticket Sample
Generated using iText

Styled like an actual airline ticket

Includes: Passenger name, flight no, date, time, source, destination, etc.

Auto-sent to user via email after booking


📨 Email Notifications
User receives confirmation email upon registration

Ticket is sent to user’s email after booking

Admin receives welcome message upon creation

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/YashAirways-Reservation-System-Yash-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

🚀 Deployment (Free)
Deployed using Railway:

Automatically builds from GitHub

Free tier for backend hosting

MongoDB Atlas used for cloud DB


🧠 Learning Highlights
Advanced Spring Boot and Spring Security

JWT integration in real-world application

Daily task scheduling with @Scheduled

PDF generation with custom styling

Real-time email service integration

Secure role-based access control

🙋‍♂️ Author
Yash Gupta

📧 guptay264@gmail.com

🌐 GitHub

💼 Aspiring Java Backend Developer



