package sanatorium.medicalAppointment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanatorium.medicalAppointment.dto.MedicalAppointmentRequestDto;
import sanatorium.medicalAppointment.dto.MedicalAppointmentResponseDto;
import sanatorium.medicalAppointment.service.IMedicalAppointmentService;

import java.util.List;

@Tag(name="MedicalAppointmentController",description = "Operations related to medical appointments.")
@RestController
@RequestMapping("/api/appointment")
public class MedicalAppointmentController {
    private final IMedicalAppointmentService medicalAppointmentService;

    public MedicalAppointmentController(IMedicalAppointmentService medicalAppointmentService) {
        this.medicalAppointmentService = medicalAppointmentService;
    }

    @GetMapping("/patient/dni/{dni}")
    ResponseEntity<List<MedicalAppointmentResponseDto>> getMedicalAppointmentsByPatientDni(@PathVariable String dni){
        List<MedicalAppointmentResponseDto> list=medicalAppointmentService.getAppointmentsByPatientDni(dni);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    ResponseEntity<MedicalAppointmentResponseDto> createMedicalAppointment(@RequestBody MedicalAppointmentRequestDto medicalAppointmentRequestDto){
        MedicalAppointmentResponseDto medicalAppointmentResponseDto=medicalAppointmentService.createAppointment(medicalAppointmentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalAppointmentResponseDto);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteMedicalAppointment(@PathVariable Long id){
        medicalAppointmentService.deleteAppointmentById(id);
        return ResponseEntity.ok("Medical appointment deleted successfully");
    }

}
