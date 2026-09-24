# Cash Card API

A small REST API for managing cash cards, built with **Spring Boot**, **Spring Data JDBC**, and an **H2 in-memory database**.

This is the initial version of the project. The goal is to keep the foundation simple and clean while leaving room for future additions such as validation, authentication, authorization, PostgreSQL, API documentation, and improved error handling.

## Tech Stack

- Java 17+
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JDBC
- H2 Database
- Maven
- JUnit 5 / Spring Boot Test

## Architecture

The application follows a simple layered structure:

```text
Controller → Service → Repository → H2 Database
```

### Project Structure

```text
src/
├── main/
│   ├── java/example/cashcard/
│   │   ├── CashCard.java
│   │   ├── CashCardApplication.java
│   │   ├── CashCardController.java
│   │   ├── CashCardRepository.java
│   │   └── service/
│   │       └── CashCardService.java
│   │
│   └── resources/
│       ├── application.properties
│       ├── schema.sql
│       └── data.sql
│
└── test/
    ├── java/example/cashcard/
    │   ├── CashCardApplicationTests.java
    │   └── CashCardJsonTest.java
    └── resources/
        ├── data.sql
        └── example/cashcard/
            ├── list.json
            └── single.json
```

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven 3.9+

Verify your installation:

```bash
java -version
mvn -version
```

### Run the application

Clone the repository and run:

```bash
mvn spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

### Run tests

```bash
mvn clean test
```

## API Endpoints

### Get a cash card

```http
GET /cashcards/{id}
```

Example:

```http
GET /cashcards/99
```

### Create a cash card

```http
POST /cashcards
Content-Type: application/json
```

Example request body:

```json
{
  "amount": 250.00,
  "owner": "sarah1"
}
```

The API returns `201 Created` and provides the location of the newly created resource in the `Location` header.

### List cash cards

```http
GET /cashcards
```

Pagination:

```http
GET /cashcards?page=0&size=2
```

Sorting:

```http
GET /cashcards?page=0&size=2&sort=amount,desc
```

When pagination parameters are omitted, the API uses a default page size of `20` and sorts by `amount` in ascending order.

## H2 Database

The application uses an in-memory H2 database for development and demonstration purposes.

The database schema and initial records are created automatically from:

```text
src/main/resources/schema.sql
src/main/resources/data.sql
```

### H2 Console

The H2 console is available at:

```text
http://localhost:8080/h2-console
```

Connection details:

```text
JDBC URL: jdbc:h2:mem:cashcarddb;DB_CLOSE_ON_EXIT=FALSE
User Name: sa
Password: [leave empty]
```

Because this is an in-memory database, data is reset when the application stops.

## Testing

The project contains integration tests covering:

- Retrieving an existing cash card
- Handling an unknown cash card ID
- Creating a cash card
- Listing cash cards
- Pagination
- Sorting
- Default pagination and sorting
- JSON serialization and deserialization

## Future Improvements

Possible next steps for the project include:

- Request/response DTOs
- Bean Validation
- Global exception handling
- Proper API error responses
- Authentication and authorization
- Ownership-based access control
- PostgreSQL
- Database migrations with Flyway
- OpenAPI / Swagger documentation
- More comprehensive unit and integration tests
- Docker support

## License

This project is currently intended as a personal learning and portfolio project.
