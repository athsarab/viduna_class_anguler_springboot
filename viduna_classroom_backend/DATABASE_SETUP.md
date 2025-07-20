# Viduna Classroom - Database Setup

## Prerequisites
- MySQL 8.0 or higher
- MySQL Workbench (optional, for GUI management)

## Database Setup Steps

### 1. Install MySQL
Download and install MySQL from [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/)

### 2. Create Database
```sql
-- Connect to MySQL as root user
mysql -u root -p

-- Create the database
CREATE DATABASE viduna_classroom;

-- Create a user for the application (optional, you can use root)
CREATE USER 'classroom_user'@'localhost' IDENTIFIED BY 'your_password_here';

-- Grant all privileges on the database
GRANT ALL PRIVILEGES ON viduna_classroom.* TO 'classroom_user'@'localhost';

-- Flush privileges
FLUSH PRIVILEGES;

-- Exit MySQL
EXIT;
```

### 3. Update Application Properties
In `src/main/resources/application.properties`, update the database configuration:

```properties
# Update the password to match your MySQL root password
spring.datasource.password=your_mysql_password_here

# Or if you created a specific user:
spring.datasource.username=classroom_user
spring.datasource.password=your_password_here
```

### 4. Tables Creation
The application will automatically create the necessary tables when you first run it, thanks to:
```properties
spring.jpa.hibernate.ddl-auto=update
```

### 5. Expected Tables
The following tables will be created automatically:
- `users` - Stores student and admin user information
- `classrooms` - Stores class information
- `student_marks` - Stores student marks and grades

### 6. Sample Data (Optional)
You can insert some sample data to test the application:

```sql
USE viduna_classroom;

-- Insert sample admin user (password: admin123)
INSERT INTO users (email, password, first_name, last_name, role, created_at) 
VALUES ('admin@viduna.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Admin', 'User', 'ADMIN', NOW());

-- Insert sample student (password: student123)
INSERT INTO users (email, password, first_name, last_name, role, created_at) 
VALUES ('student@viduna.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'John', 'Doe', 'STUDENT', NOW());

-- Insert sample classroom
INSERT INTO classrooms (title, description, teacher_name, teacher_email, subject, schedule, created_at, updated_at)
VALUES ('Introduction to Programming', 'Learn the basics of programming with Java', 'Prof. Smith', 'smith@viduna.com', 'Computer Science', 'Mon-Wed-Fri 10:00 AM', NOW(), NOW());
```

Note: The passwords above are hashed versions of 'admin123' and 'student123' using BCrypt.

## Troubleshooting

### Connection Issues
- Ensure MySQL service is running
- Check if the port 3306 is available
- Verify username and password in application.properties
- Make sure the database name is correct

### Permission Issues
- Ensure the user has proper privileges on the database
- Check firewall settings if connecting remotely

### Table Creation Issues
- Check that the application.properties file is properly configured
- Verify that the MySQL connector dependency is in the classpath
- Check application logs for detailed error messages
