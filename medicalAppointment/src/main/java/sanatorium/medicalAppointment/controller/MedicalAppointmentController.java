package sanatorium.medicalAppointment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sanatorium.medicalAppointment.dto.MedicalAppointmentRequestDto;
import sanatorium.medicalAppointment.dto.MedicalAppointmentResponseDto;
import sanatorium.medicalAppointment.service.IMedicalAppointmentService;

@Tag(name="Medical Appointment",description = "Operations related to medical appointments.")
@RestController("/api/appointment")
public class MedicalAppointmentController {
    private final IMedicalAppointmentService medicalAppointmentService;

    public MedicalAppointmentController(IMedicalAppointmentService medicalAppointmentService) {
        this.medicalAppointmentService = medicalAppointmentService;
    }

    @GetMapping("/create")
    ResponseEntity<MedicalAppointmentResponseDto> createMedicalAppointment(MedicalAppointmentRequestDto medicalAppointmentRequestDto){
        MedicalAppointmentResponseDto medicalAppointmentResponseDto=medicalAppointmentService.createAppointment(medicalAppointmentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalAppointmentResponseDto);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteMedicalAppointment(Long id){
        medicalAppointmentService.deleteAppointmentById(id);
        return ResponseEntity.ok("Medical appointment deleted successfully");
    }

}
