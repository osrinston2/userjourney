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

### Option A: Using Terminal / Command Line

#### 1. Clone & Navigate
Clone the repository (or navigate to the project directory):
```bash
cd userjourney
```

#### 2. Build the Application
Use the Maven wrapper to clean and build the project:

- **On Windows (PowerShell / Command Prompt):**
  ```cmd
  .\mvnw.cmd clean package
  ```

- **On Linux / macOS:**
  ```bash
  ./mvnw clean package
  ```

#### 3. Run the Application
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

---

### Option B: Using IntelliJ IDEA IDE

#### 1. Open the Project
1. Launch **IntelliJ IDEA**.
2. Click **File** > **Open...** (or select **Open** on the Welcome screen).
3. Select the `userjourney` root directory containing the `pom.xml` file.
4. Select **Open as Project**. IntelliJ will automatically detect Maven and sync all dependencies.

#### 2. Configure JDK 21
1. Go to **File** > **Project Structure...** (`Ctrl + Alt + Shift + S` / `Cmd + ;`).
2. Under **Project Settings** > **Project**, set **SDK** to **21 (Java 21)**.
3. Ensure **Language level** is set to `SDK default` or `21 - Sequel to preview features / Java 21`.
4. Click **Apply** and **OK**.

#### 3. Enable Annotation Processing (Required for Lombok)
1. Go to **Settings** (`Ctrl + Alt + S` on Windows/Linux) or **Preferences** (`Cmd + ,` on macOS).
2. Navigate to **Build, Execution, Deployment** > **Compiler** > **Annotation Processors**.
3. Check the box for **Enable annotation processing**.
4. Click **Apply** and **OK**.

#### 4. Run the Application
- **Method 1 (Main Class):**
  1. In the Project tool window (`Alt + 1`), navigate to [`src/main/java/com/project/userjourney/UserjourneyApplication.java`](file:///c:/github/userjourney/src/main/java/com/project/userjourney/UserjourneyApplication.java).
  2. Right-click [`UserjourneyApplication.java`](file:///c:/github/userjourney/src/main/java/com/project/userjourney/UserjourneyApplication.java) and select **Run 'UserjourneyApplication'** (or click the green ▶ play icon next to the `main` method).

- **Method 2 (Maven Tool Window):**
  1. Open the **Maven** tool window on the right sidebar (`View` > `Tool Windows` > `Maven`).
  2. Expand `userjourney` > `Plugins` > `spring-boot`.
  3. Double-click **`spring-boot:run`**.

The application will start on port **`8080`** by default.

---

### Option C: Using Visual Studio Code (VS Code)

#### 1. Recommended Extensions
Install the following extensions from the VS Code Marketplace:
- **Extension Pack for Java** (Microsoft)
- **Spring Boot Extension Pack** (VMware)
- **Lombok Annotations Support for VS Code** (Gabriel B. R.)

#### 2. Open the Project
1. Launch **Visual Studio Code**.
2. Go to **File** > **Open Folder...** (`Ctrl + K, Ctrl + O` / `Cmd + O`).
3. Select the `userjourney` workspace folder.
4. VS Code will automatically detect the Maven project and prompt to import Java projects.

#### 3. Configure Java SDK 21
1. Open the Command Palette (`Ctrl + Shift + P` / `Cmd + Shift + P`).
2. Type and select **Java: Configure Java Runtime**.
3. Under **Project JDKs**, ensure the Java Toolchain / Project SDK is pointing to **JDK 21**.

#### 4. Run the Application
- **Method 1 (Code Lens):**
  1. Open [`src/main/java/com/project/userjourney/UserjourneyApplication.java`](file:///c:/github/userjourney/src/main/java/com/project/userjourney/UserjourneyApplication.java).
  2. Click the **Run** or **Debug** button that appears above `public static void main` (Line 9).

- **Method 2 (Spring Boot Dashboard):**
  1. Click on the **Spring** icon on the left Activity Bar to open the Spring Boot Dashboard.
  2. Find `userjourney` under Apps and click the **Start** ▶ icon.

- **Method 3 (Built-in Terminal):**
  1. Open terminal (`Ctrl + ` `).
  2. Run `.\mvnw.cmd spring-boot:run` (Windows) or `./mvnw spring-boot:run` (macOS/Linux).

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
