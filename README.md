# Spring Boot Todo Application

A RESTful API Todo application built with Spring Boot 3.5.5, Java 21, and PostgreSQL. This project demonstrates modern Spring Boot development practices with clean architecture, DTOs, and proper separation of concerns.

## 🚀 Features

- **Complete CRUD Operations**: Create, Read, Update, and Delete todo items
- **RESTful API Design**: Standard REST endpoints with proper HTTP methods
- **PostgreSQL Integration**: Robust relational database with JPA/Hibernate
- **DTO Pattern**: Clean separation between entities and API contracts
- **Validation**: Input validation using Jakarta Bean Validation
- **Docker Support**: Easy database setup with Docker Compose
- **Builder Pattern**: Flexible entity creation with custom builder
- **Automatic Timestamps**: CreatedAt and UpdatedAt fields with JPA lifecycle hooks

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Java 21** or higher
- **Maven 3.6+**
- **Docker** and **Docker Compose** (for running PostgreSQL)
- **Git** (for cloning the repository)

## 🛠️ Tech Stack

- **Spring Boot 3.5.5** - Framework
- **Java 21** - Programming Language
- **PostgreSQL 16** - Database
- **Spring Data JPA** - Data Access Layer
- **Hibernate** - ORM
- **Lombok** - Boilerplate Code Reduction
- **Maven** - Build Tool
- **Docker** - Containerization

## 📦 Installation

### 1. Clone the Repository

```bash
git clone <repository-url>
cd spring-boot-todo-app
```

### 2. Start PostgreSQL with Docker

```bash
docker-compose up -d
```

This will start a PostgreSQL container with the following configuration:
- **Host**: localhost
- **Port**: 5433
- **Database**: todo_db
- **Username**: todo_user
- **Password**: secret

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Endpoints

### Get All Todos

```http
GET /api/todos
```

**Response**: `200 OK`
```json
[
  {
    "title": "Complete project",
    "description": "Finish the Spring Boot project",
    "completed": false,
    "createdAt": "2024-01-15T10:30:00",
    "dueAt": "2024-01-20T18:00:00"
  }
]
```

### Get Todo by ID

```http
GET /api/todos/{id}
```

or

```http
GET /api/todos/todo?id={id}
```

**Response**: `200 OK`
```json
{
  "title": "Complete project",
  "description": "Finish the Spring Boot project",
  "completed": false,
  "createdAt": "2024-01-15T10:30:00",
  "dueAt": "2024-01-20T18:00:00"
}
```

### Create Todo

```http
POST /api/todos
```

**Request Body**:
```json
{
  "title": "New Task",
  "description": "Task description",
  "completed": false,
  "dueAt": "2024-01-20T18:00:00"
}
```

**Response**: `201 CREATED`

### Update Todo (Full Update)

```http
PUT /api/todos/{id}
```

**Request Body**:
```json
{
  "title": "Updated Task",
  "description": "Updated description",
  "completed": true,
  "dueAt": "2024-01-25T18:00:00"
}
```

**Response**: `200 OK`

### Update Todo (Partial Update)

```http
PATCH /api/todos/{id}
```

**Request Body** (all fields optional):
```json
{
  "completed": true
}
```

**Response**: `200 OK`

### Delete Todo

```http
DELETE /api/todos/{id}
```

**Response**: `204 NO CONTENT`

## 🏗️ Project Structure

```
src/main/java/com/berkedev/springboottodoapp/
├── controller/
│   └── TodoController.java          # REST endpoints
├── service/
│   ├── TodoService.java             # Service interface
│   └── impl/
│       └── TodoServiceImpl.java     # Service implementation
├── data/
│   ├── entity/
│   │   └── Todo.java                # JPA entity
│   ├── repository/
│   │   └── TodoRepository.java      # JPA repository
│   ├── dto/
│   │   ├── TodoCreateRequest.java   # Create request DTO
│   │   ├── TodoUpdateRequest.java   # Update request DTO
│   │   └── TodoResponse.java        # Response DTO
│   └── mapper/
│       └── TodoMapper.java          # Entity-DTO mapper
├── exception/
│   └── GlobalExceptionHandler.java  # Global exception handling
└── SpringBootTodoAppApplication.java # Main application class
```

## 🗄️ Database Schema

The application uses a single `todo` table with the following structure:

| Column      | Type            | Constraints       |
|-------------|-----------------|-------------------|
| id          | BIGINT          | PRIMARY KEY, AUTO |
| title       | VARCHAR         | NOT NULL          |
| description | VARCHAR         | NULLABLE          |
| completed   | BOOLEAN         | NOT NULL, DEFAULT false |
| due_at      | TIMESTAMP       | NULLABLE          |
| created_at  | TIMESTAMP       | NOT NULL          |
| updated_at  | TIMESTAMP       | NULLABLE          |

## ⚙️ Configuration

The application can be configured via `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/todo_db
    username: todo_user
    password: secret
  jpa:
    hibernate:
      ddl-auto: update  # Change to 'validate' in production
    properties:
      hibernate:
        format_sql: true
        show_sql: true
server:
  port: 8080
```

## 🧪 Testing

Run tests with:

```bash
mvn test
```

## 🐳 Docker Commands

Start the database:
```bash
docker-compose up -d
```

Stop the database:
```bash
docker-compose down
```

View database logs:
```bash
docker logs todo-postgres
```

## 📝 Notes

- The application uses **Hibernate's ddl-auto: update** for development. For production, consider using database migration tools like Flyway or Liquibase.
- All timestamps are configured for **Europe/Istanbul** timezone
- The application includes SQL logging for debugging (can be disabled in production)
- ID generation uses PostgreSQL sequences for better performance

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

**Berke Genç**
- GitHub: [@berkedev](https://github.com/gencberke)

## 🙏 Acknowledgments

- Spring Boot Team for the excellent framework
- PostgreSQL Community for the robust database system
- All contributors who help improve this project
