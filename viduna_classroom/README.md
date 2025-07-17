# Viduna Classroom - Online Learning Platform

A comprehensive online classroom management system built with **Angular 19 + Spring Boot + MySQL**. This platform allows students to view classes and their marks, while administrators can manage classes, upload images, and track student performance.

## 🚀 Features

### For Students
- **User Registration & Login** - Secure authentication system
- **Class Dashboard** - View all available classes with teacher details
- **Marks Tracking** - Check exam results and grades
- **Responsive Design** - Works on desktop, tablet, and mobile devices

### For Administrators
- **Class Management** - Create, edit, and delete classes
- **Image Upload** - Upload class images and teacher photos
- **Student Management** - View registered students
- **Marks Management** - Add, edit, and delete student marks
- **Real-time Updates** - Dynamic content management

## 🛠️ Technology Stack

### Frontend
- **Angular 19** - Modern web framework
- **Bootstrap 5** - Responsive UI components
- **TypeScript** - Type-safe development
- **RxJS** - Reactive programming

### Backend
- **Spring Boot 3.2** - Java web framework
- **Spring Security** - Authentication & authorization
- **JWT** - Secure token-based authentication
- **JPA/Hibernate** - Database ORM
- **Maven** - Dependency management

### Database
- **MySQL 8** - Relational database
- **File Storage** - Local file system for images

## ⚙️ Installation & Setup

### Prerequisites
- **Node.js 18+** and npm
- **Java 17+**
- **MySQL 8+**
- **Angular CLI** (`npm install -g @angular/cli`)

### 1. Database Setup

```sql
-- Create database
CREATE DATABASE viduna_classroom;

-- Create user (optional)
CREATE USER 'classroom_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON viduna_classroom.* TO 'classroom_user'@'localhost';
FLUSH PRIVILEGES;
```

### 2. Backend Setup

```bash
# Navigate to backend directory
cd viduna_classroom_backend

# Update database credentials in application.properties
# Edit src/main/resources/application.properties:
spring.datasource.username=root
spring.datasource.password=your_password_here

# Run the application
./mvnw spring-boot:run
# OR on Windows:
mvnw.cmd spring-boot:run
```

The backend will start on `http://localhost:8080`

### 3. Frontend Setup

```bash
# Navigate to frontend directory
cd viduna_classroom

# Install dependencies
npm install

# Start development server
ng serve
# OR on a different port:
ng serve --port 4200
```

The frontend will start on `http://localhost:4200`

## 📋 API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Classes (Admin only for CUD operations)
- `GET /api/classes` - Get all classes
- `POST /api/classes` - Create new class
- `PUT /api/classes/{id}` - Update class
- `DELETE /api/classes/{id}` - Delete class
- `POST /api/classes/{id}/upload-image` - Upload class image
- `POST /api/classes/{id}/upload-teacher-image` - Upload teacher image

### Marks
- `GET /api/marks` - Get all marks (Admin only)
- `GET /api/marks/student/{id}` - Get student marks
- `POST /api/marks` - Create mark (Admin only)
- `PUT /api/marks/{id}` - Update mark (Admin only)
- `DELETE /api/marks/{id}` - Delete mark (Admin only)

### Users
- `GET /api/users/students` - Get all students (Admin only)
- `GET /api/users/{id}` - Get user by ID

## 🐛 Troubleshooting

### Common Issues

1. **CORS Error**
   - Ensure backend CORS is configured for frontend URL
   - Check `SecurityConfig.java` CORS settings

2. **Database Connection**
   - Verify MySQL is running
   - Check credentials in `application.properties`
   - Ensure database exists

3. **File Upload Issues**
   - Check upload directory permissions
   - Verify file size limits in `application.properties`

## Development server

To start a local development server, run:

```bash
ng serve
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To execute unit tests with the [Karma](https://karma-runner.github.io) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

```bash
ng e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
