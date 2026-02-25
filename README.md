# Spring Security Basic Auth Demo

This project demonstrates **Spring Security (Spring Boot 3)** using:

- Basic Authentication  
- Role-based Authorization  
- In-Memory Users  
- Secure API Endpoints  
- Custom `SecurityFilterChain` configuration  

---

## 🚀 Features

### 🔐 Authentication
Project uses **Basic Auth** with these in-memory users:

| Username | Password  | Role  |
|----------|-----------|--------|
| admin    | admin123  | ADMIN |
| user     | user123   | USER  |

Passwords are encoded using **BCrypt**.

---

## 🔒 Authorization Rules (Endpoints)

| Method | Endpoint              | Access            |
|--------|------------------------|--------------------|
| POST   | `/contacts`           | ADMIN only        |
| DELETE | `/contacts/**`        | ADMIN only        |
| GET    | `/contacts`           | USER, ADMIN       |
| GET    | `/contacts/public/**` | Public (no auth)  |
| ANY    | others                | Authenticated     |

---

## 🔧 Security Configuration (Summary)

- CSRF disabled  
- HTTP Basic enabled  
- Frame options disabled (for H2 / dev tools)  
- Request matchers applied for role-based access  

