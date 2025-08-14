# Restaurant Reservation Java (Spring Boot)

This is a Java Spring Boot port of the original C# RestaurantReservationMongo project. It provides RESTful APIs for managing restaurant reservations, users, and restaurants, using MongoDB as the backend database.

## Features
- Manage reservations, users, and restaurants
- Prevents double-booking of tables (2-hour time slot logic)
- Full CRUD operations for all entities
- Unit tests for controllers and services

## Project Structure
```
restaurant-reservation-java/
├── src/main/java/com/example/restaurantreservation/
│   ├── controller/         # REST Controllers
│   ├── model/              # MongoDB Entities (POJOs)
│   └── service/            # Business Logic Services
├── src/test/java/com/example/restaurantreservation/
│   ├── controller/         # Controller Unit Tests
│   └── service/            # Service Unit Tests
├── src/main/resources/
│   └── application.properties # Spring Boot & MongoDB config
├── pom.xml                 # Maven build file
└── README.md               # This file
```

## API Documentation (Swagger/OpenAPI)

After starting the application, you can access the interactive OpenAPI docs at:

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

This is powered by Springdoc OpenAPI. Use Swagger UI to:
- Try out all endpoints live
- View the OpenAPI spec in JSON/YAML
- Share API docs with frontend developers or integrators

## Requirements
- **Java:** 17 or newer
- **Maven:** 3.6+
- **MongoDB:** 5.0+ (default URI: `mongodb://localhost:27017/restaurantdb`)

## Running the Application
1. Make sure MongoDB is running locally, or update `application.properties` for a different URI.
2. Build and run:
   ```bash
   mvn clean package
   mvn spring-boot:run
   ```

## Running Unit Tests
```bash
mvn test
```

## API Endpoints
### Reservations
- `GET /api/reservations` — List all reservations
- `GET /api/reservations/{id}` — Get reservation by ID
- `POST /api/reservations` — Create a reservation (prevents double-booking)
- `PUT /api/reservations/{id}` — Update reservation
- `DELETE /api/reservations/{id}` — Delete reservation

### Restaurants
- `GET /api/restaurants` — List all restaurants
- `GET /api/restaurants/{id}` — Get restaurant by ID
- `POST /api/restaurants` — Create a restaurant
- `PUT /api/restaurants/{id}` — Update restaurant
- `DELETE /api/restaurants/{id}` — Delete restaurant

### Users
- `GET /api/users` — List all users
- `GET /api/users/{id}` — Get user by ID
- `POST /api/users` — Create a user
- `PUT /api/users/{id}` — Update user
- `DELETE /api/users/{id}` — Delete user

## Configuration
Edit `src/main/resources/application.properties` to change the MongoDB URI or server port.

## How to Contribute
1. Fork this repo and create a feature branch.
2. Make your changes and add/modify unit tests as needed.
3. Submit a pull request with a clear description of your changes.

## License
MIT

---

**Converted from C# to Java Spring Boot by Cascade AI.**
