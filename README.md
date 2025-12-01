# Webhook SQL Solver

A Spring Boot application that automatically solves a SQL problem and submits the solution via webhook on startup.

## Overview

This application performs the following actions on startup:

1. Sends a POST request to generate a webhook URL and access token
2. Builds a SQL query to solve the problem statement
3. Submits the SQL solution to the generated webhook URL using JWT authentication

## Problem Statement

For every department, calculate the average age of individuals with salaries exceeding ₹70,000, and produce a concatenated string containing at most 10 of their names.

**Output Format:**
- DEPARTMENT_NAME: The name of the department
- AVERAGE_AGE: The average age of employees earning more than ₹70,000
- EMPLOYEE_LIST: Comma-separated list of up to 10 employee names (format: FIRST_NAME LAST_NAME)

Results are ordered by department ID in descending order.

## Technology Stack

- Java 17
- Spring Boot 3.2.0
- Maven
- RestTemplate for HTTP calls

## Building and Running

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build
```bash
mvn clean install
```

### Run
```bash
mvn spring-boot:run
```

Or run the JAR file:
```bash
java -jar target/webhook-sql-solver-1.0.0.jar
```

## Project Structure

```
src/
├── main/
│   ├── java/com/bajajfinserv/
│   │   ├── WebhookSqlSolverApplication.java    # Main application class
│   │   ├── component/
│   │   │   └── WebhookStartupComponent.java    # Startup component
│   │   ├── config/
│   │   │   └── RestTemplateConfig.java         # RestTemplate configuration
│   │   ├── dto/
│   │   │   ├── WebhookRequest.java             # Request DTO for webhook generation
│   │   │   ├── WebhookResponse.java            # Response DTO from webhook generation
│   │   │   └── SolutionRequest.java            # Request DTO for solution submission
│   │   └── service/
│   │       ├── WebhookService.java             # Main service for webhook flow
│   │       └── SqlQueryBuilder.java            # SQL query builder
│   └── resources/
│       └── application.properties               # Application configuration
```

## How It Works

1. On application startup, `WebhookStartupComponent` is initialized via `@PostConstruct`
2. It calls `WebhookService.processWebhookFlow()` which:
   - Generates a webhook by sending a POST request with user details
   - Receives the webhook URL and JWT access token
   - Builds the SQL query using `SqlQueryBuilder`
   - Submits the solution to the webhook URL with JWT authentication

## SQL Query Logic

The SQL query:
- Joins DEPARTMENT, EMPLOYEE, and PAYMENTS tables
- Filters employees with total salary > ₹70,000
- Calculates average age per department
- Groups employee names (FIRST_NAME LAST_NAME) with comma separation
- Limits to 10 names per department
- Orders results by department ID in descending order

## Download JAR File

### Pre-built JAR (Recommended)
Download the ready-to-run JAR file:

**Raw GitHub Link:**
```
https://raw.githubusercontent.com/ludwinsubbaiahhh/sql-solver/main/releases/webhook-sql-solver-1.0.0.jar
```

**Direct Download:**
```
https://github.com/ludwinsubbaiahhh/sql-solver/raw/main/releases/webhook-sql-solver-1.0.0.jar
```

### Run the JAR
```bash
java -jar webhook-sql-solver-1.0.0.jar
```

The application will automatically:
1. Generate a webhook on startup
2. Build and execute the SQL query
3. Submit the solution via the webhook with JWT authentication

