# Aplikasi Laundry - Java Swing Desktop Management System

A desktop-based enterprise management system designed for laundry and dry-cleaning business operations. Developed using Java Swing and MySQL with JDBC connectivity, the software streamlines customer registration, order ticketing, service pricing calculations, and transaction settlement.

## Overview

Aplikasi Laundry centralizes daily laundry shop workflows into a single desktop interface. The system automates order tracking, calculates total pricing based on package weight and service type, manages customer records, and records completed financial transactions to reduce human calculation errors and operational overhead.


## Interface Preview

[![Interface Preview Status](https://img.shields.io/badge/Interface_Preview-Documentation_in_Progress-0052cc?style=for-the-badge&logo=java&logoColor=white)](https://github.com/ANTARTICA1/Aplikasi-Laundry)

## Core Features

- Operator Authentication: Secure login portal protecting administrative and cashier access.
- Customer Data Management (FormCustomer): Complete CRUD capabilities (Create, Read, Update, Delete) for recording customer names, phone numbers, and addresses.
- Order & Service Ticketing (Transaksi): Support for multiple laundry service packages (cuci basah, cuci kering, setrika, express) with automated unit/weight multiplication.
- Payment & Invoice Processing (FormPembayaran): Automated balance calculation, cash received validation, change computation, and invoice logging.
- Main Dashboard & Records Grid (MenuUtama): Interactive JTable data grid styled with custom blue themes for reviewing order logs, payment status, and order queues.
- Relational Database Persistence: Centralized storage managed via JDBC (Java Database Connectivity) with MySQL Connector/J.

## Architecture and Database Schema

```
[ Operator Login ]
        │
        ▼
[ Main Dashboard (MenuUtama) ]
   ├── [ Customer Management (FormCustomer) ] ──► (Customers Table)
   ├── [ Order Processing (Transaksi) ]       ──► (Transactions Table)
   └── [ Payment Settlement (FormPembayaran) ] ──► (Payments & Invoices Table)
        │
        ▼
[ MySQL Database (laundrydb via JDBC) ]
```

## Technology Stack

- Language: Java (JDK 8 / JDK 11+)
- GUI Framework: Java Swing / AWT (NetBeans GUI Builder Form)
- Database: MySQL / MariaDB
- Database Driver: MySQL Connector/J 9.5.0 (`mysql-connector-j-9.5.0.jar`)
- Build Tool: Apache Ant (`build.xml`) / NetBeans IDE

## Project Structure

```
Aplikasi-Laundry/
├── lib/
│   └── mysql-connector-j-9.5.0.jar   # MySQL JDBC Driver library
├── nbproject/                         # NetBeans project configuration
├── src/
│   └── aplikasilaundry/
│       ├── Customer.java              # Customer model entity
│       ├── FormCustomer.java          # Customer management interface & CRUD
│       ├── FormCustomer.form          # GUI builder layout definition
│       ├── FormPembayaran.java        # Payment and transaction settlement UI
│       ├── FormPembayaran.form        # Payment form layout definition
│       ├── Koneksi.java               # JDBC database connection factory
│       ├── Login.java                 # Authentication window
│       ├── Login.form                 # Login window layout
│       ├── MainMenu.java              # Entry point navigation helper
│       ├── MenuUtama.java             # Main application dashboard and tables
│       ├── MenuUtama.form             # Dashboard layout
│       ├── Pembayaran.java            # Payment domain logic and entity
│       └── Transaksi.java             # Transaction entity and processing
├── build.xml                          # Ant build script
└── README.md                          # Technical documentation
```

## Setup and Installation

### Prerequisites

- Java Development Kit (JDK 8 or higher)
- MySQL Server (Localhost or LAN network)
- NetBeans IDE or IntelliJ IDEA / Eclipse

### Database Setup

1. Start your MySQL service.
2. Create the target database:
   ```sql
   CREATE DATABASE laundrydb;
   ```
3. Ensure user credentials match `src/aplikasilaundry/Koneksi.java`:
   ```java
   String url = "jdbc:mysql://localhost:3306/laundrydb";
   String user = "root";
   String pass = "";
   ```

### Running the Application

#### Option 1: Via NetBeans IDE
1. Open NetBeans IDE.
2. Select **File > Open Project** and choose the `Aplikasi-Laundry` directory.
3. Ensure `lib/mysql-connector-j-9.5.0.jar` is included in Project Libraries.
4. Clean and Build the project (Shift + F11).
5. Run the project (F6) or right-click `Login.java` > **Run File**.

#### Option 2: Via Command Line (Ant)
1. Clone the repository:
   ```bash
   git clone https://github.com/ANTARTICA1/Aplikasi-Laundry.git
   cd Aplikasi-Laundry
   ```
2. Build and run:
   ```bash
   ant compile
   ant run
   ```

## Author

Anak Agung Ngurah Krisna Artha Wibawa ([ANTARTICA1](https://github.com/ANTARTICA1))

## License

This project is licensed under the MIT License.