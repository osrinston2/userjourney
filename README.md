# UserJourney - Travel Insurance Backend API

## Description
**UserJourney** is a Spring Boot RESTful web application designed to manage the customer application flow for travel insurance policies. It provides endpoints for retrieving form configuration (areas, plans, and coverages), calculating pricing dynamically, validating customer and plan inputs, and processing travel insurance submissions.

---

## Technical Stack & Dependencies

### Prerequisites
To build and run this application locally, you need:
- **Java**: JDK 21 or higher
- **Maven**: Apache Maven 3.8+ (or use the provided Maven Wrapper `./mvnw` / `mvnw.cmd`)
- **Database**: SQLite (embedded, pre-configured with `userjourney.db`)

### Core Dependencies & Frameworks
- **Spring Boot 3.x / Web MVC**: Core framework for building REST APIs
- **Spring Data JPA & Hibernate**: ORM and data access layer
- **Hibernate Community Dialects & SQLite JDBC**: Database integration for SQLite
- **Spring Boot Starter Validation**: Request payload validation (`jakarta.validation`)
- **Lombok**: Boilerplate reduction (getters, setters, constructors)
- **Springdoc OpenAPI UI**: Interactive API documentation (Swagger UI)

---

## Localhost Setup & Running Guide

### 1. Clone & Navigate
Clone the repository (or navigate to the project directory):
```bash
cd userjourney
```

### 2. Build the Application
Use the Maven wrapper to clean and build the project:

- **On Windows (PowerShell / Command Prompt):**
  ```cmd
  .\mvnw.cmd clean package
  ```

- **On Linux / macOS:**
  ```bash
  ./mvnw clean package
  ```

### 3. Run the Application
You can run the application using Maven:

- **On Windows:**
  ```cmd
  .\mvnw.cmd spring-boot:run
  ```

- **On Linux / macOS:**
  ```bash
  ./mvnw spring-boot:run
  ```

Alternatively, run the compiled executable JAR file directly:
```bash
java -jar target/userjourney-0.0.1-SNAPSHOT.jar
```

The application will start on port **`8080`** by default.

---

## API Documentation & Access Points

Once the server is running on localhost, access the following URLs:

- **Swagger UI (Interactive API Docs):** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI Specification (JSON):** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

### Main API Endpoints (`/journey`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/journey/load` | Retrieves form metadata (areas, coverage types, plans) |
| `GET` | `/journey/price/{area}/{coverage}` | Retrieves pricing options filtered by area and coverage |
| `POST` | `/journey/plandetail/validate` | Validates travel plan details |
| `POST` | `/journey/customerdetail/validate` | Validates customer details |
| `POST` | `/journey/insurance/submit` | Submits the completed travel insurance application |

---

## Database Configuration

The application uses an embedded **SQLite** database (`userjourney.db` in the project root). Database settings can be reviewed or modified in [`src/main/resources/application.properties`](file:///c:/github/userjourney/src/main/resources/application.properties):

```properties
spring.application.name=userjourney
spring.datasource.url=jdbc:sqlite:userjourney.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
```
