#!/bin/bash

echo "🚀 Starting all microservices..."
echo "================================"

# Verify that we are in the correct directory
if [ ! -d "patient" ] || [ ! -d "doctor" ] || [ ! -d "medicalAppointment" ]; then
    echo "❌ Error: Make sure you're in the root directory with patient/, doctor/, and medicalAppointment/ folders"
    exit 1
fi

# Export environment variables to ensure they're available to subprocesses
export MY_SQL_PWD=${MY_SQL_PWD}

# Function to kill existing processes
cleanup() {
    echo ""
    echo "🛑 Stopping all services..."

    # Kill the specific Maven processes if they exist
    if [ ! -z "$PATIENT_PID" ]; then
        kill $PATIENT_PID 2>/dev/null
    fi
    if [ ! -z "$DOCTOR_PID" ]; then
        kill $DOCTOR_PID 2>/dev/null
    fi
    if [ ! -z "$APPOINTMENT_PID" ]; then
        kill $APPOINTMENT_PID 2>/dev/null
    fi

    # Kill any remaining spring-boot:run processes
    pkill -f "spring-boot:run" 2>/dev/null

    echo "✅ All services stopped"
    exit 0
}

# Capture Ctrl+C to make cleanup
trap cleanup SIGINT

echo "🔍 Starting Patient Service (Port 9001)..."
(cd patient && MY_SQL_PWD=$MY_SQL_PWD ../mvnw spring-boot:run > ../patient.log 2>&1) &
PATIENT_PID=$!

echo "🔍 Starting Doctor Service (Port 9002)..."
(cd doctor && MY_SQL_PWD=$MY_SQL_PWD ../mvnw spring-boot:run > ../doctor.log 2>&1) &
DOCTOR_PID=$!

echo "🔍 Starting Medical Appointment Service (Port 9003)..."
(cd medicalAppointment && MY_SQL_PWD=$MY_SQL_PWD ../mvnw spring-boot:run > ../appointment.log 2>&1) &
APPOINTMENT_PID=$!

echo ""
echo "✅ All services are starting up..."
echo "================================"
echo "📍 Patient Service:      http://localhost:9001"
echo "📍 Doctor Service:       http://localhost:9002"
echo "📍 Appointment Service:  http://localhost:9003"
echo ""

# Function to check if a service is ready
check_service() {
    local url=$1
    local name=$2
    local max_attempts=30
    local attempt=1

    while [ $attempt -le $max_attempts ]; do
        if curl -s "$url" > /dev/null 2>&1; then
            echo "✅ $name is ready!"
            return 0
        fi
        if [ $attempt -eq 1 ]; then
            echo "⏳ Waiting for $name to start..."
        fi
        sleep 2
        attempt=$((attempt + 1))
    done

    echo "❌ $name failed to start after $max_attempts attempts"
    echo "💡 Check the logs: $name.log"
    return 1
}

echo "🔍 Checking service health..."
echo "================================"

# Wait a bit for services to start
sleep 5

# Check each service (using a simple endpoint that should work)
patient_ready=false
doctor_ready=false
appointment_ready=false

echo "🩺 Checking Patient Service..."
if check_service "http://localhost:9001/api/patient/dni/test" "Patient Service"; then
    patient_ready=true
fi

echo "👨‍⚕️ Checking Doctor Service..."
if check_service "http://localhost:9002/api/doctor?lastName=test" "Doctor Service"; then
    doctor_ready=true
fi

echo "📅 Checking Medical Appointment Service..."
if check_service "http://localhost:9003/api/appointment" "Medical Appointment Service"; then
    appointment_ready=true
fi

echo ""
echo "================================"

# Show final results
if [ "$patient_ready" = true ] && [ "$doctor_ready" = true ] && [ "$appointment_ready" = true ]; then
    echo "🎉 SUCCESS! All microservices are running properly!"
    echo "🏥 Your Sanatorium system is ready to serve patients!"
    echo ""
    echo "🚀 Ready for testing:"
    echo "📍 Patient API:      http://localhost:9001/api/patient"
    echo "📍 Doctor API:       http://localhost:9002/api/doctor"
    echo "📍 Appointment API:  http://localhost:9003/api/appointment"
    echo ""
    echo "📚 Swagger Documentation:"
    echo "📍 Patient Swagger:      http://localhost:9001/swagger-ui/index.html"
    echo "📍 Doctor Swagger:       http://localhost:9002/swagger-ui/index.html"
    echo "📍 Appointment Swagger:  http://localhost:9003/swagger-ui/index.html"
    echo ""
    echo "📋 Logs available in: patient.log, doctor.log, appointment.log"
else
    echo "⚠️  Some services failed to start properly:"
    [ "$patient_ready" = false ] && echo "❌ Patient Service - Check patient.log"
    [ "$doctor_ready" = false ] && echo "❌ Doctor Service - Check doctor.log"
    [ "$appointment_ready" = false ] && echo "❌ Medical Appointment Service - Check appointment.log"
    echo ""
    echo "💡 Common issues:"
    echo "   - Check MySQL is running and credentials are correct"
    echo "   - Verify ports 9001, 9002, 9003 are available"
    echo "   - Review the log files for specific errors"
fi

echo ""
echo "🛑 Press Ctrl+C to stop all services"

# Wait for all background processes
wait