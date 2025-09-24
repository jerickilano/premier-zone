# Database Connection Troubleshooting Guide

## Issues Identified

1. **Empty Database**: Your PostgreSQL database is likely empty - no data from `prem_stats.csv` has been loaded
2. **No Data Loading Mechanism**: There was no service to populate the database with CSV data
3. **Connection Verification Needed**: Need to verify PostgreSQL is running and accessible

## Solutions Implemented

### 1. Data Loading Service
Created `DataLoaderService.java` that:
- Automatically loads data from `prem_stats.csv` on application startup
- Maps CSV columns to Player entity fields
- Handles data parsing and validation
- Provides loading progress feedback

### 2. Database Health Check
Created `DatabaseHealthController.java` with endpoint:
- `GET /api/v1/health/database` - Check database connection status
- Returns player count and connection status
- Helps diagnose connection issues

### 3. Database Configuration
Created `DatabaseConfig.java` for explicit database configuration

### 4. Connection Testing
Created `DatabaseConnectionTest.java` for automated testing

## Step-by-Step Troubleshooting

### Step 1: Verify PostgreSQL is Running
```bash
# Check if PostgreSQL is running on port 5432
netstat -an | findstr :5432

# Or try to connect directly
psql -h localhost -p 5432 -U postgres -d postgres
```

### Step 2: Check Database Credentials
Verify your `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=jerick23
```

### Step 3: Start the Application
```bash
mvn spring-boot:run
```

### Step 4: Check Health Endpoint
```bash
curl http://localhost:8080/api/v1/health/database
```

Expected response:
```json
{
  "status": "UP",
  "message": "Database connection successful",
  "playerCount": 608,
  "timestamp": 1703123456789
}
```

### Step 5: Test Data Loading
```bash
# Check if data was loaded
curl http://localhost:8080/api/v1/player

# Should return array of player objects
```

## Common Issues and Solutions

### Issue 1: PostgreSQL Not Running
**Solution**: Start PostgreSQL service
```bash
# Windows (if installed as service)
net start postgresql-x64-13

# Or start manually
pg_ctl -D "C:\Program Files\PostgreSQL\13\data" start
```

### Issue 2: Wrong Database Credentials
**Solution**: Update `application.properties` with correct credentials

### Issue 3: Database Doesn't Exist
**Solution**: Create the database
```sql
CREATE DATABASE postgres;
```

### Issue 4: Permission Issues
**Solution**: Grant proper permissions
```sql
GRANT ALL PRIVILEGES ON DATABASE postgres TO postgres;
```

### Issue 5: Port Already in Use
**Solution**: Check what's using port 5432
```bash
netstat -ano | findstr :5432
```

## Testing the Complete Flow

1. **Start PostgreSQL** (if not running)
2. **Run the application**: `mvn spring-boot:run`
3. **Check logs** for data loading messages
4. **Test health endpoint**: `curl http://localhost:8080/api/v1/health/database`
5. **Test data retrieval**: `curl http://localhost:8080/api/v1/player`
6. **Test filtering**: `curl "http://localhost:8080/api/v1/player?team=Arsenal"`

## Expected Results

- Application starts without errors
- Data loading completes with message: "Data loading completed successfully!"
- Health endpoint returns status "UP" with player count > 0
- Player endpoints return real data from database
- Filtering works correctly (by team, name, position)

## Next Steps

1. Run the application and check the console output
2. Test the health endpoint to verify connection
3. Test the player endpoints to verify data loading
4. If issues persist, check PostgreSQL logs and application logs
