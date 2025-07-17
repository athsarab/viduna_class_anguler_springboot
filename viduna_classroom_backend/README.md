# 🎓 Viduna Classroom - Online Learning Management System

A comprehensive online classroom platform built with **Angular 19** (Frontend), **Spring Boot 3** (Backend), and **MySQL** (Database). This system allows students to view classes, check their marks, and enables administrators to manage classes, students, and marks efficiently.

## ✨ Features

### 👨‍🎓 Student Features
- **User Registration & Login** - Secure authentication system
- **View Classes** - Browse available classes with teacher information
- **Check Marks** - View personal academic performance and grades
- **Modern UI** - Responsive design that works on all devices

### 👨‍💼 Admin Features
- **Class Management** - Create, update, and delete classes
- **Student Management** - View all registered students
- **Marks Management** - Add, edit, and delete student marks
- **Image Upload** - Upload class images and teacher photos
- **Comprehensive Dashboard** - Manage all aspects of the classroom

### 🔧 Technical Features
- **JWT Authentication** - Secure token-based authentication
- **Role-based Access Control** - Different permissions for students and admins
- **File Upload** - Support for image uploads
- **Responsive Design** - Works seamlessly on desktop and mobile
- **RESTful APIs** - Clean and well-documented API endpoints

## 🛠️ Technology Stack

### Frontend (Angular 19)
- **Angular 19** - Latest version with standalone components
- **Bootstrap 5** - Modern UI framework
- **Angular Material** - UI components
- **RxJS** - Reactive programming
- **TypeScript** - Type-safe development

### Backend (Spring Boot 3)
- **Spring Boot 3** - Modern Java framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **MySQL Connector** - Database connectivity
- **JWT** - Token-based authentication
- **Maven** - Build management

### Database
- **MySQL 8.0** - Reliable relational database

## 🚀 Quick Start

### Prerequisites
- **Node.js** 18+ and npm
- **Java** 17+
- **MySQL** 8.0+
- **Angular CLI** 19+

### 🔧 Installation

#### 1. Clone the Repository
```bash
git clone <repository-url>
cd viduna_classroom
```

#### 2. Setup Database
```sql
-- Create database
CREATE DATABASE viduna_classroom;

-- Update password in backend/src/main/resources/application.properties
spring.datasource.password=your_mysql_password
```

#### 3. Start Backend (Spring Boot)
```bash
cd viduna_classroom_backend

# Install dependencies and run
./mvnw spring-boot:run

# Or on Windows
mvnw.cmd spring-boot:run
```
Backend will start on `http://localhost:8080`

#### 4. Start Frontend (Angular)
```bash
cd viduna_classroom

# Install dependencies
npm install

# Start development server
ng serve
```
Frontend will start on `http://localhost:4200`

## 📱 Usage

### For Students:
1. **Register** - Create a new student account
2. **Login** - Access your student dashboard
3. **View Classes** - Browse available classes and teacher information
4. **Check Marks** - View your academic performance

### For Administrators:
1. **Login** - Use admin credentials
2. **Manage Classes** - Create, edit, or delete classes
3. **Upload Images** - Add class and teacher photos
4. **Manage Marks** - Add student marks and grades
5. **View Students** - See all registered students

## 🏗️ Project Structure

```
viduna_classroom/
├── viduna_classroom/                 # Angular Frontend
│   ├── src/
│   │   ├── app/
│   │   │   ├── models/              # TypeScript interfaces
│   │   │   ├── services/            # API services
│   │   │   ├── login/               # Login component
│   │   │   ├── student-dashboard/   # Student dashboard
│   │   │   └── admin-dashboard/     # Admin dashboard
│   │   ├── assets/                  # Static assets
│   │   └── styles.css               # Global styles
│   ├── package.json
│   └── angular.json
│
└── viduna_classroom_backend/         # Spring Boot Backend
    ├── src/main/java/com/viduna/classroom/
    │   ├── entity/                  # JPA entities
    │   ├── repository/              # Data repositories
    │   ├── service/                 # Business logic
    │   ├── controller/              # REST controllers
    │   ├── dto/                     # Data transfer objects
    │   └── security/                # Security configuration
    ├── src/main/resources/
    │   └── application.properties   # Configuration
    ├── pom.xml
    └── DATABASE_SETUP.md
```

## 🔌 API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration

### Classes
- `GET /api/classes` - Get all classes
- `POST /api/classes` - Create class (Admin only)
- `PUT /api/classes/{id}` - Update class (Admin only)
- `DELETE /api/classes/{id}` - Delete class (Admin only)
- `POST /api/classes/{id}/upload-image` - Upload class image (Admin only)

### Marks
- `GET /api/marks` - Get all marks (Admin only)
- `GET /api/marks/student/{id}` - Get student marks
- `POST /api/marks` - Create mark (Admin only)
- `PUT /api/marks/{id}` - Update mark (Admin only)
- `DELETE /api/marks/{id}` - Delete mark (Admin only)

### Users
- `GET /api/users/students` - Get all students (Admin only)
- `GET /api/users` - Get all users (Admin only)

## 🎨 Screenshots

### Student Dashboard
- Clean, modern interface for students
- View classes with teacher information
- Check personal marks and grades

### Admin Dashboard
- Comprehensive management interface
- Create and manage classes
- Upload images for classes and teachers
- Manage student marks

## 🔐 Security Features

- **JWT Authentication** - Secure token-based auth
- **Role-based Access** - Different permissions for students/admins
- **Password Encryption** - BCrypt password hashing
- **CORS Configuration** - Secure cross-origin requests
- **Input Validation** - Server-side validation for all inputs

## 🌐 Browser Support

- Chrome (recommended)
- Firefox
- Safari
- Edge

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Support

For support, email support@viduna.com or create an issue in the repository.

## 🎯 Future Enhancements

- Real-time notifications
- Assignment submissions
- Video conferencing integration
- Mobile applications
- Advanced reporting and analytics
- Email notifications
- Calendar integration

---

**Built with ❤️ for education**
