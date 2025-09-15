# Bank Management System

A full-stack banking portal application built with a monolithic Spring Boot backend and an Angular frontend. This system provides role-based access for managers, employees, and customers, each with a unique set of functionalities.

## Features ✨

-   **Role-Based Access Control**: Separate login and dashboards for Managers, Employees, and Customers.
-   **Manager Portal**:
    -   Register, view, update, and soft-delete employees.
    -   Create, view, update, and soft-delete customer accounts.
-   **Employee Portal**:
    -   Deposit and withdraw funds from customer accounts.
    -   View a paginated list of all customers.
-   **Customer Portal**:
    -   Securely transfer funds to other accounts with password confirmation.
    -   View current account balance.
    -   View a paginated transaction history (account statement).
    -   Update personal details and reset passwords.

## Built With 🛠️

**Backend**:

-   **Java 21**
-   **Spring Boot**
-   **Spring Data JPA**
-   **Apache Derby** (Embedded Database)
-   **Maven**

**Frontend**:

-   **Angular**
-   **TypeScript**
-   **HTML & CSS**

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

-   **JDK 21** or higher
-   **Node.js and npm**
-   **Angular CLI**: `npm install -g @angular/cli`
-   **Eclipse IDE** or any other Java IDE

### Installation & Setup

1.  **Clone the repo**
    ```sh
    git clone https://github.com/ridamEXE/banking-system.git
    ```
2.  **Configure the Backend (Spring Boot)**:
    -   Import the backend project into your IDE as a Maven project.
    -   Create a folder on your computer for the database (e.g., `C:/bank_database` or `/Users/YourUser/bank_database`).
    -   Open `src/main/resources/application.properties` and update the database URL to your chosen path:
        ```properties
        spring.datasource.url=jdbc:derby:db;create=true
        ```
    -   Run the main application file to start the server.

3.  **Configure the Frontend (Angular)**:
    -   Navigate to the frontend project folder in your terminal:
        ```sh
        cd FrontEnd
        ```
    -   Install NPM packages:
        ```sh
        npm install
        ```
    -   Run the development server:
        ```sh
        ng serve
        ```
    -   Open your browser and navigate to `http://localhost:4200`.
