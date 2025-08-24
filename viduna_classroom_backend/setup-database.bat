@echo off
echo 🔧 Setting up Viduna Classroom Database...
echo.
echo Please enter your MySQL root password when prompted.
echo.

REM Create database and user
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS viduna_classroom; CREATE USER IF NOT EXISTS 'classroom_user'@'localhost' IDENTIFIED BY 'classroom123'; GRANT ALL PRIVILEGES ON viduna_classroom.* TO 'classroom_user'@'localhost'; FLUSH PRIVILEGES; SHOW DATABASES;"

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ✅ Database setup completed successfully!
    echo.
    echo Database: viduna_classroom
    echo Username: classroom_user  
    echo Password: classroom123
    echo.
    echo Now update your application.properties with these credentials.
) else (
    echo.
    echo ❌ Database setup failed. Please check your MySQL root password.
    echo.
    echo Alternative: Use MySQL Workbench to create the database manually.
)

pause
