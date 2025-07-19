#!/bin/bash

echo "🚀 Starting all microservices..."
echo "================================"

# Verify that we are in the correct directory
if [ ! -d "patient" ] || [ ! -d "doctor" ] || [ ! -d "medicalAppointment" ]; then
    echo "❌ Error: Make sure you're in the root directory with patient/, doctor/, and medicalAppointment/ folders"
    exit 1
fi

# Function to kill existing processes (optional)
cleanup() {
    echo "🛑 Stopping all services..."
    pkill -f "spring-boot:run"
    exit 0
}

# Capture Ctrl+C to make cleanup
trap cleanup SIGINT

echo "🔍 Starting Patient Service (Port 9001)..."
cd patient
../mvnw spring-boot:run &
PATIENT_PID=$!
cd ..

echo "🔍 Starting Doctor Service (Port 9002)..."
cd doctor
../mvnw spring-boot:run &
DOCTOR_PID=$!
cd ..

echo "🔍 Starting Medical Appointment Service (Port 9003)..."
cd medicalAppointment
../mvnw spring-boot:run &
APPOINTMENT_PID=$!
cd ..

echo ""
echo "✅ All services are starting up..."
echo "================================"
echo "📍 Patient Service:      http://localhost:9001"
echo "📍 Doctor Service:       http://localhost:9002"
echo "📍 Appointment Service:  http://localhost:9003"
echo ""
echo "🔧 Swagger URLs:"
echo "📍 Patient Swagger:      http://localhost:9001/swagger-ui.html"
echo "📍 Doctor Swagger:       http://localhost:9002/swagger-ui.html"
echo "📍 Appointment Swagger:  http://localhost:9003/swagger-ui.html"
echo ""
echo "⏳ Services are starting... Wait a moment for them to be ready"
echo "🛑 Press Ctrl+C to stop all services"


wait