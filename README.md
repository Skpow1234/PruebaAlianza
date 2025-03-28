# Alianza Technical Test

## Backend Setup

### Prerequisites

- Java 17
- Maven 3.6+

### Installation and Running

```bash
cd Backend
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### API Documentation

Swagger UI is available at: `http://localhost:8080/swagger-ui.html`

### Available Endpoints

- GET `/api/clients` - Get all clients
- GET `/api/clients?sharedKey={key}` - Search by shared key
- GET `/api/clients?sharedKey={key}&businessId={id}&email={email}` - Advanced search
- POST `/api/clients` - Create new client

## Frontend Setup

### Prerequisites

- Node.js 14+
- npm 6+

### Installation and Running

```bash
cd Frontend
npm install
ng serve
```

The frontend will start on `http://localhost:4200`

## Features

- Client Management
  - List all clients
  - Create new clients
  - Search clients by shared key
  - Advanced search functionality
- Responsive Design
- Form Validations
- CSV Export

## Testing

### Backend Tests

```bash
cd Backend
mvn test
```

### Frontend Tests

```bash
cd Frontend
ng test
```

## Technologies Used

- Backend:
  - Spring Boot 3.4
  - Java 17
  - Maven
  - Lombok
  - MapStruct
  - Swagger
  - JUnit 5
- Frontend:
  - Angular 13+
  - TypeScript
  - Angular Material
  - SCSS
