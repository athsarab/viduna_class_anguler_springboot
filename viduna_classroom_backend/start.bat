@echo off
REM Viduna Classroom Backend Startup Script for Windows

echo 🚀 Starting Viduna Classroom Backend...

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Java is not installed. Please install Java 17 or higher.
    pause
    exit /b 1
)

echo ✅ Java is installed
echo 📦 Building application...

REM Build the application
call mvnw.cmd clean package -DskipTests

if %errorlevel% equ 0 (
    echo ✅ Build successful
    echo 🚀 Starting application on port 8080...
    java -jar target\classroom-backend-0.0.1-SNAPSHOT.jar
) else (
    echo ❌ Build failed. Please check the logs above.
    pause
    exit /b 1
)
