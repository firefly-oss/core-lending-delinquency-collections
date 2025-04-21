# Core Lending Delinquency & Collections Service

## Overview
The Core Lending Delinquency & Collections Service is a microservice component of the Firefly platform that manages loan delinquency and collections processes. This service provides APIs for tracking delinquent loans, managing collection cases, recording collection actions, tracking payment promises, and handling escalations.

## Architecture

### Components
The service is built as a multi-module Maven project with the following components:

1. **core-lending-delinquency-collections-interfaces**
   - Contains DTOs (Data Transfer Objects)
   - Defines enums and constants
   - Provides the API contract

2. **core-lending-delinquency-collections-models**
   - Contains entity definitions
   - Defines database repositories
   - Includes database migration scripts

3. **core-lending-delinquency-collections-core**
   - Implements business logic
   - Contains service implementations
   - Provides mappers between entities and DTOs

4. **core-lending-delinquency-collections-web**
   - Exposes REST API endpoints
   - Handles HTTP requests and responses
   - Contains the application entry point

### Technology Stack
- **Language**: Java 21 (with virtual threads)
- **Framework**: Spring Boot 3.x
- **API**: Spring WebFlux (reactive)
- **Database Access**: R2DBC (Reactive Relational Database Connectivity)
- **Database**: PostgreSQL
- **Migration**: Flyway
- **Documentation**: OpenAPI/Swagger
- **Build Tool**: Maven

## Setup and Installation

### Prerequisites
- Java 21 or higher
- Maven 3.8 or higher
- PostgreSQL 14 or higher
- Docker (optional, for containerized deployment)

### Environment Variables
The following environment variables need to be set:

```
DB_HOST=localhost
DB_PORT=5432
DB_NAME=collections_db
DB_USERNAME=postgres
DB_PASSWORD=your_password
DB_SSL_MODE=disable
```

### Building the Application
```bash
mvn clean install
```

### Running the Application
```bash
# Run with Maven
mvn spring-boot:run -pl core-lending-delinquency-collections-web

# Run the JAR file
java -jar core-lending-delinquency-collections-web/target/core-lending-delinquency-collections-web-1.0.0-SNAPSHOT.jar
```

### Running with Docker
```bash
# Build the Docker image
docker build -t core-lending-delinquency-collections .

# Run the Docker container
docker run -p 8080:8080 \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=5432 \
  -e DB_NAME=collections_db \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=your_password \
  -e DB_SSL_MODE=disable \
  core-lending-delinquency-collections
```

## API Documentation

The service provides a RESTful API with the following main endpoints:

### Collection Cases
- `GET /api/v1/collection-cases` - List or search collection cases
- `POST /api/v1/collection-cases` - Create a new collection case
- `GET /api/v1/collection-cases/{collectionCaseId}` - Get a collection case by ID
- `PUT /api/v1/collection-cases/{collectionCaseId}` - Update a collection case
- `DELETE /api/v1/collection-cases/{collectionCaseId}` - Delete a collection case

### Collection Actions
- `GET /api/v1/collection-actions` - List collection actions
- `POST /api/v1/collection-actions` - Create a new collection action
- `GET /api/v1/collection-actions/{actionId}` - Get a collection action by ID

### Collection Promises
- `GET /api/v1/collection-promises` - List payment promises
- `POST /api/v1/collection-promises` - Create a new payment promise
- `PUT /api/v1/collection-promises/{promiseId}` - Update a payment promise

### Collection Escalations
- `GET /api/v1/collection-escalations` - List escalations
- `POST /api/v1/collection-escalations` - Create a new escalation
- `GET /api/v1/collection-escalations/{escalationId}` - Get an escalation by ID

### Swagger UI
When running in development mode, the Swagger UI is available at:
```
http://localhost:8080/swagger-ui.html
```

## Database Information

### Schema
The service uses a PostgreSQL database with the following main tables:

1. **collection_case** - Stores information about delinquent loans
   - Tracks loan status, days past due, delinquency stage
   - Records amounts due and recovered
   - Manages case lifecycle (opened/closed dates)

2. **collection_status_history** - Tracks status changes of collection cases
   - Records old and new statuses
   - Stores reason codes for status changes
   - Maintains audit information

3. **collection_action** - Records actions taken on collection cases
   - Stores action types (calls, emails, visits, etc.)
   - Tracks outcomes of actions
   - Includes notes and timestamps

4. **collection_promise** - Tracks payment promises made by borrowers
   - Records promised amounts and dates
   - Tracks actual payments
   - Monitors whether promises were kept

5. **collection_escalation** - Manages escalation of collection cases
   - Tracks escalation levels
   - Records escalation reasons
   - Includes notes and timestamps

### Migrations
Database migrations are managed using Flyway and are located in:
```
core-lending-delinquency-collections-models/src/main/resources/db/migration/
```

## Development Guidelines

### Code Structure
- Follow the package structure based on feature and version
- Use interfaces for services
- Implement mappers using MapStruct
- Follow reactive programming patterns with Reactor

### Testing
- Write unit tests for services and controllers
- Use reactive test utilities for testing Mono and Flux
- Mock external dependencies

### Versioning
- API versioning is done in the URL path (e.g., `/api/v1/`)
- Package versioning follows the same pattern (e.g., `v1` packages)

## Monitoring and Observability

The service exposes the following actuator endpoints:
- Health: `/actuator/health`
- Info: `/actuator/info`
- Prometheus metrics: `/actuator/prometheus`

## Deployment

### Kubernetes
The service can be deployed to Kubernetes using the provided Dockerfile.

### Configuration Profiles
The application supports the following Spring profiles:
- `dev` - Development environment with detailed logging
- `testing` - Testing environment with API docs enabled
- `prod` - Production environment with minimal logging and disabled API docs