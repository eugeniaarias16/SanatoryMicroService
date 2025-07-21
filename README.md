

# 🏥 Sanatorium Microservices

A comprehensive medical appointment management system built with Spring Boot microservices architecture.

## 📋 Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Testing](#testing)


## 🎯 Overview

The Sanatorium Microservices system manages medical appointments through three independent services:
- **Patient Service**: Manages patient information and profiles
- **Doctor Service**: Handles doctor data and specialties
- **Medical Appointment Service**: Coordinates appointments between patients and doctors

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Patient       │    │   Doctor        │    │   Medical       │
│   Service       │    │   Service       │    │   Appointment   │
│   Port: 9001    │    │   Port: 9002    │    │   Service       │
└─────────────────┘    └─────────────────┘    │   Port: 9003    │
         │                       │             └─────────────────┘
         └───────────────────────┼─────────────────────┘
                                 │
                    ┌─────────────────┐
                    │     MySQL       │
                    │    Database     │
                    └─────────────────┘
```

### Service Dependencies
- **Patient Service**: Standalone service managing patient data
- **Doctor Service**: Standalone service managing doctor information
- **Medical Appointment Service**: Coordinates data from both Patient and Doctor services

## ✨ Features

### Patient Management
- ✅ Create, update, and delete patient profiles
- ✅ Search patients by DNI or ID
- ✅ Partial updates (PATCH operations)
- ✅ Unique DNI validation

### Doctor Management
- ✅ Manage doctor profiles and specialties
- ✅ Search by name, specialty, or combinations
- ✅ Salary and contact information management
- ✅ Full and partial update operations

### Medical Appointments
- ✅ Schedule appointments between patients and doctors
- ✅ Flexible date and time formatting
- ✅ Appointment history and management
- ✅ Integrated patient and doctor information

## 🛠️ Tech Stack

- **Framework**: Spring Boot 3.2.5
- **Language**: Java 21
- **Database**: MySQL 8.0
- **Documentation**: OpenAPI 3 (Swagger)
- **Build Tool**: Maven
- **ORM**: Spring Data JPA / Hibernate
- **Validation**: Jakarta Bean Validation
- **Utilities**: Lombok

## 📋 Prerequisites

Before running the application, make sure you have:

- **Java 21** or higher
- **MySQL 8.0** or higher
- **Maven 3.6+** (or use included Maven wrapper)
- **Git**

## 🚀 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/sanatorium-microservices.git
cd sanatorium-microservices
```

### 2. Database Setup
```sql
CREATE DATABASE patient_db;
CREATE DATABASE doctor_db;
CREATE DATABASE medical_appointment_db;

-- Create user (optional)
CREATE USER 'sanatorium'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON patient_db.* TO 'sanatorium'@'localhost';
GRANT ALL PRIVILEGES ON doctor_db.* TO 'sanatorium'@'localhost';
GRANT ALL PRIVILEGES ON medical_appointment_db.* TO 'sanatorium'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Environment Configuration
Set your MySQL password as an environment variable:
```bash
export MY_SQL_PWD=your_mysql_password
```

### 4. Update Application Properties
Each service has its own `application.properties` file. Update the database connections:

```properties
# Example for patient service
spring.datasource.url=jdbc:mysql://localhost:3306/patient_db
spring.datasource.username=root
spring.datasource.password=${MY_SQL_PWD}
spring.jpa.hibernate.ddl-auto=update
```

## 🎮 Usage

### Quick Start - All Services
Use the provided script to start all services simultaneously:

```bash
chmod +x start-all.sh
./start-all.sh
```

The script will:
- ✅ Start all three services
- ✅ Verify each service is healthy
- ✅ Display success message with all URLs
- ✅ Create individual log files for debugging

### Manual Start
Start each service individually:

```bash
# Terminal 1 - Patient Service
cd patient
../mvnw spring-boot:run

# Terminal 2 - Doctor Service  
cd doctor
../mvnw spring-boot:run

# Terminal 3 - Medical Appointment Service
cd medicalAppointment
../mvnw spring-boot:run
```

### Service URLs
- **Patient Service**: http://localhost:9001
- **Doctor Service**: http://localhost:9002
- **Medical Appointment Service**: http://localhost:9003

## 📚 API Documentation

### Swagger UI Access
You can find the documentation of each api and the integrated documentation of the Sanatorium System in the folder **📁 docs - SanatoryMicroService/docs**.

Or if you prefer you can download each api here:
- **Patient API**: http://localhost:9001/swagger-ui/index.html
- **Doctor API**: http://localhost:9002/swagger-ui/index.html
- **Appointment API**: http://localhost:9003/swagger-ui/index.html

### Key Endpoints

#### Patient Service (`/api/patient`)
```
GET    /api/patient/{id}              - Get patient by ID
GET    /api/patient/dni/{dni}         - Get patient by DNI
POST   /api/patient/create            - Create new patient
PUT    /api/patient/updateById/{id}   - Update patient by ID
PATCH  /api/patient/update/id/{id}    - Partial update patient
DELETE /api/patient/delete/{id}       - Delete patient
```

#### Doctor Service (`/api/doctor`)
```
GET    /api/doctor/id/{id}                          - Get doctor by ID
GET    /api/doctor?lastName={name}                  - Search doctors
POST   /api/doctor/create                          - Create new doctor
PUT    /api/doctor/id/{id}                         - Update doctor
DELETE /api/doctor/id/{id}                         - Delete doctor
```

#### Medical Appointment Service (`/api/appointment`)
```
GET    /api/appointment/patient/dni/{dni}  - Get appointments by patient DNI
POST   /api/appointment/create             - Create new appointment
DELETE /api/appointment/delete/{id}        - Delete appointment
```

## 🧪 Testing

### Example API Calls

#### Create a Doctor
```bash
curl -X POST http://localhost:9002/api/doctor/create \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Juan",
    "lastName": "Pérez", 
    "medicalSpecialty": "Cardiología",
    "salary": 50000.0,
    "address": "Av. Corrientes 1234",
    "numberAddress": "1234",
    "phoneNumber": "+5491123456789"
  }'
```

#### Create a Patient
```bash
curl -X POST http://localhost:9001/api/patient/create \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "María",
    "lastName": "González",
    "dni": "12345678",
    "phoneNumber": "+5491987654321", 
    "birthDate": "1990-05-15"
  }'
```

#### Create an Appointment
```bash
curl -X POST http://localhost:9003/api/appointment/create \
  -H "Content-Type: application/json" \
  -d '{
    "patientDni": "12345678",
    "doctorFirstName": "Juan",
    "doctorLastName": "Pérez",
    "doctorSpecialty": "Cardiología",
    "appointmentDate": "25/07/2025",
    "appointmentTime": "14:30"
  }'
```


## 👥 Authors

- **Eugenia M. Arias** -  [MyGithub](https://github.com/eugeniaarias16)



---



**Happy coding! 🚀**
