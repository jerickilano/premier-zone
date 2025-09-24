# Premier-Zone Software Documentation

## Table of Contents
1. [Project Overview](#project-overview)
2. [System Architecture](#system-architecture)
3. [Technology Stack](#technology-stack)
4. [Database Design](#database-design)
5. [API Documentation](#api-documentation)
6. [Code Structure](#code-structure)
7. [Configuration](#configuration)
8. [Getting Started](#getting-started)
9. [Future Enhancements](#future-enhancements)

## Project Overview

**Premier-Zone** is a Spring Boot REST API application designed to manage and provide access to Premier League player statistics. The application serves as a backend service for football analytics, fantasy sports, or statistical analysis platforms.

### Purpose
- Centralized management of Premier League player data
- RESTful API for CRUD operations on player statistics
- Support for complex queries and filtering
- Foundation for football analytics applications

### Key Features
- Complete CRUD operations for player data
- Flexible querying with multiple filter options
- RESTful API design following industry standards
- PostgreSQL database integration
- Comprehensive player statistics tracking

## System Architecture

The application follows a **3-tier architecture pattern**:

```
┌─────────────────┐
│   Controller    │ ← HTTP Requests/Responses
│   (REST API)    │
└─────────────────┘
         │
┌─────────────────┐
│    Service      │ ← Business Logic
│    Layer        │
└─────────────────┘
         │
┌─────────────────┐
│   Repository    │ ← Data Access
│    (JPA)        │
└─────────────────┘
         │
┌─────────────────┐
│   PostgreSQL    │ ← Database
│    Database     │
└─────────────────┘
```

### Layer Responsibilities

1. **Controller Layer** (`PlayerController`)
   - Handles HTTP requests and responses
   - Validates input parameters
   - Delegates business logic to service layer
   - Returns appropriate HTTP status codes

2. **Service Layer** (`PlayerService`)
   - Contains business logic
   - Implements data filtering and processing
   - Manages transactions
   - Coordinates with repository layer

3. **Repository Layer** (`PlayerRepository`)
   - Data access abstraction
   - Extends JpaRepository for CRUD operations
   - Custom query methods
   - Database interaction management

## Technology Stack

### Backend Framework
- **Java 21** - Programming language
- **Spring Boot 3.3.13** - Application framework
- **Spring Data JPA** - Data persistence
- **Spring Web** - REST API framework

### Database
- **PostgreSQL** - Primary database
- **Hibernate** - ORM (Object-Relational Mapping)

### Build Tool
- **Maven** - Dependency management and build automation

### Development Tools
- **Spring Boot DevTools** - Development utilities
- **Spring Boot Test** - Testing framework

## Database Design

### Entity: Player
**Table Name:** `player_statistic`

| Field | Type | Constraints | Description |
|-------|------|-------------|-------------|
| name | VARCHAR | PRIMARY KEY, UNIQUE | Player's full name |
| nation | VARCHAR | - | Player's nationality |
| pos | VARCHAR | - | Playing position (FW, MF, DF, GK) |
| age | INTEGER | - | Player's age |
| mp | INTEGER | - | Matches played |
| starts | INTEGER | - | Matches started |
| min | DOUBLE | - | Minutes played |
| gls | DOUBLE | - | Goals scored |
| ast | DOUBLE | - | Assists |
| pk | DOUBLE | - | Penalties |
| crdy | DOUBLE | - | Yellow cards |
| crdr | DOUBLE | - | Red cards |
| xg | DOUBLE | - | Expected goals |
| xag | DOUBLE | - | Expected assists |
| team | VARCHAR | - | Current team |

### Database Configuration
- **Host:** localhost:5432
- **Database:** postgres
- **Username:** postgres
- **Password:** j23
- **Hibernate DDL:** update (auto-creates/updates tables)

## API Documentation

### Base URL
```
http://localhost:8080/api/v1/player
```

### Endpoints

#### 1. Get All Players
```http
GET /api/v1/player
```
**Description:** Retrieves all players in the database

**Response:** Array of Player objects
```json
[
  {
    "name": "Player Name",
    "nation": "Country",
    "pos": "Position",
    "age": 25,
    "mp": 30,
    "starts": 28,
    "min": 2520.0,
    "gls": 15.0,
    "ast": 8.0,
    "pk": 2.0,
    "crdy": 3.0,
    "crdr": 0.0,
    "xg": 12.5,
    "xag": 6.2,
    "team": "Team Name"
  }
]
```

#### 2. Get Players by Team
```http
GET /api/v1/player?team={teamName}
```
**Parameters:**
- `team` (optional): Team name to filter by

#### 3. Search Players by Name
```http
GET /api/v1/player?name={playerName}
```
**Parameters:**
- `name` (optional): Player name to search for (case-insensitive)

#### 4. Get Players by Team and Position
```http
GET /api/v1/player?team={teamName}&position={position}
```
**Parameters:**
- `team` (optional): Team name
- `position` (optional): Player position

#### 5. Add New Player
```http
POST /api/v1/player
Content-Type: application/json
```
**Request Body:** Player object
**Response:** Created Player object (HTTP 201)

#### 6. Update Player
```http
PUT /api/v1/player
Content-Type: application/json
```
**Request Body:** Updated Player object
**Response:** Updated Player object (HTTP 200) or Not Found (HTTP 404)

#### 7. Delete Player
```http
DELETE /api/v1/player/{playerName}
```
**Parameters:**
- `playerName`: Name of player to delete
**Response:** Success message (HTTP 200)

### HTTP Status Codes
- `200 OK` - Successful GET, PUT, DELETE
- `201 Created` - Successful POST
- `404 Not Found` - Resource not found
- `400 Bad Request` - Invalid request data

## Code Structure

```
src/main/java/com/pl/premier_zone/
├── PremierZoneApplication.java          # Main application class
└── player/
    ├── Player.java                      # Entity class
    ├── PlayerController.java            # REST controller
    ├── PlayerService.java               # Business logic
    └── PlayerRepository.java            # Data access interface
```

### Key Classes

#### Player.java
- JPA Entity representing player data
- Maps to `player_statistic` table
- Contains all player statistics fields
- Includes constructors and getter/setter methods

#### PlayerController.java
- REST controller handling HTTP requests
- Implements all CRUD endpoints
- Supports query parameters for filtering
- Returns appropriate HTTP responses

#### PlayerService.java
- Business logic implementation
- Data filtering and processing
- Transaction management
- Repository coordination

#### PlayerRepository.java
- JPA Repository interface
- Extends JpaRepository for basic CRUD
- Custom query methods for specific operations

## Configuration

### Application Properties
```properties
spring.application.name=Premier-Zone
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=jerick23
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```

### Maven Dependencies
- `spring-boot-starter-web` - Web framework
- `spring-boot-starter-data-jpa` - JPA support
- `postgresql` - PostgreSQL driver
- `spring-boot-starter-test` - Testing framework

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd premier-zone
   ```

2. **Set up PostgreSQL database**
   - Create database: `postgres`
   - Update credentials in `application.properties` if needed

3. **Build the application**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Verify installation**
   - Application starts on `http://localhost:8080`
   - Test endpoint: `GET http://localhost:8080/api/v1/player`

### Testing the API

#### Using curl
```bash
# Get all players
curl http://localhost:8080/api/v1/player

# Get players by team
curl "http://localhost:8080/api/v1/player?team=Arsenal"

# Add new player
curl -X POST http://localhost:8080/api/v1/player \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Player","team":"Test Team","pos":"FW","age":25}'
```

## Future Enhancements

### Planned Features
1. **Authentication & Authorization**
   - JWT token-based authentication
   - Role-based access control
   - API key management

2. **Advanced Querying**
   - Pagination support
   - Sorting capabilities
   - Advanced filtering options
   - Search by multiple criteria

3. **Data Validation**
   - Input validation annotations
   - Custom validation rules
   - Error handling improvements

4. **Performance Optimizations**
   - Database indexing
   - Caching implementation
   - Query optimization

5. **Additional Features**
   - Player statistics aggregation
   - Team management endpoints
   - Historical data tracking
   - Export functionality (CSV, JSON)

6. **Monitoring & Logging**
   - Application metrics
   - Request/response logging
   - Error tracking
   - Performance monitoring

### Technical Improvements
- Add comprehensive unit tests
- Implement integration tests
- Add API documentation (Swagger/OpenAPI)
- Docker containerization
- CI/CD pipeline setup
- Database migration scripts

---

**Document Version:** 1.0  
**Last Updated:** December 2024  
**Author:** Premier-Zone Development Team

--------------------------------------------
✅ Full-Stack Premier Zone Application Complete!
Backend (Spring Boot)
✅ REST API with full CRUD operations
✅ PostgreSQL Database with 576 Premier League players
✅ CORS Configuration for frontend communication
✅ Health Check Endpoint for monitoring
✅ Clean Architecture (Controller → Service → Repository)
Frontend (React)
✅ Modern React App with beautiful UI
✅ Player Management - View, Add, Edit, Delete players
✅ Advanced Filtering - Filter by team, name, position, nation
✅ Responsive Design - Works on all devices
✅ Real-time Data - Live updates from backend
✅ User-friendly Interface - Intuitive forms and cards
Key Features:
🎯 Player Management
View All Players - Beautiful card-based grid layout
Add New Players - Comprehensive form with all player stats
Edit Players - Click edit button to modify existing players
Delete Players - Safe deletion with confirmation
Search & Filter - Find players by team, name, position, or nation
🎨 Modern UI/UX
Gradient Backgrounds - Professional look
Card-based Layout - Easy to scan player information
Smooth Animations - Hover effects and transitions
Mobile Responsive - Works perfectly on phones and tablets
Color-coded Positions - Visual indicators for player positions
⚡ Technical Features
Real-time Updates - Changes reflect immediately
Error Handling - User-friendly error messages
Loading States - Smooth loading indicators
Form Validation - Required fields and data types
CORS Enabled - Secure cross-origin communication
How to Use:
Start Backend:
Start Frontend:
Open Browser:
Frontend: http://localhost:3000
Backend API: http://localhost:8080/api/v1/player
What You Can Do:
📊 Browse 576 Premier League players with detailed stats
�� Filter players by team (Arsenal, Liverpool, etc.)
➕ Add new players with complete statistics
✏️ Edit existing players and update their stats
🗑️ Delete players from the database
📱 Use on any device - desktop, tablet, or mobile
Your Premier Zone app is now a complete, professional full-stack web application! 🚀⚽