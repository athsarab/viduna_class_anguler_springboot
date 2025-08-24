#!/bin/bash

# Viduna Classroom Backend Startup Script

echo "🚀 Starting Viduna Classroom Backend..."

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 17 or higher."
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | cut -d. -f1)
if [ "$JAVA_VERSION" -lt 17 ]; then
    echo "❌ Java 17 or higher is required. Current version: $JAVA_VERSION"
    exit 1
fi

# Check if MySQL is running
if ! pgrep -x "mysqld" > /dev/null; then
    echo "⚠️  MySQL doesn't appear to be running. Please start MySQL service."
    echo "   sudo service mysql start  # On Linux"
    echo "   brew services start mysql  # On macOS"
fi

echo "✅ Prerequisites check completed"
echo "📦 Building application..."

# Build the application
./mvnw clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Build successful"
    echo "🚀 Starting application on port 8080..."
    java -jar target/classroom-backend-0.0.1-SNAPSHOT.jar
else
    echo "❌ Build failed. Please check the logs above."
    exit 1
fi
