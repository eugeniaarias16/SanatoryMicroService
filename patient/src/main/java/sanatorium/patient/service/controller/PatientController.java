package sanatorium.patient.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import sanatorium.patient.service.DTO.PatientDto;
import sanatorium.patient.service.service.IPatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "PatientController",description = "Operations related with patients.")
@RestController
@RequestMapping("/api/patient")

public class PatientController {

    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }


    @Operation(summary = "Patient by Id", description = "Get patient by id")
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto>getPatientById(@PathVariable Long id){
        PatientDto patientDto=patientService.getPatientById(id);
        return ResponseEntity.ok(patientDto);
    }

    @Operation(summary = "Patient by Dni",description = "Get patient by dni")
    @GetMapping("/dni/{dni}")
    public ResponseEntity<PatientDto>getPatientByDni(@PathVariable String dni){
        PatientDto patientDto=patientService.getPatientByDni(dni);
        return ResponseEntity.ok(patientDto);
    }

    @Operation(summary = "Create new Patient")
    @PostMapping("/create")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto){
        PatientDto createdPatient=patientService.createPatient(patientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @Operation(summary = "Update patient by Id", description = "Update patient by id")
    @PutMapping("/updateById/{id}")
    public ResponseEntity<PatientDto> upDatePatientById(@PathVariable Long id,
                                                        @RequestBody PatientDto patientDto){
        PatientDto updatedPatient=patientService.updatePatientById(id, patientDto);
        return ResponseEntity.ok(updatedPatient);
    }

    @Operation(summary = "Update patient by Dni", description = "Update patient by dni")
    @PutMapping("/updateByDni/{dni}")
    public ResponseEntity<PatientDto> upDatePatientByDni(@PathVariable String dni,
                                                        @RequestBody PatientDto patientDto){
        PatientDto updatedPatient=patientService.upDatePatientByDni(dni, patientDto);
        return ResponseEntity.ok(updatedPatient);
    }

    @Operation(summary = "Update  partially patient's information by Id", description = "Update partially patient's information by id")
    @PatchMapping("/update/id/{id}")
    public ResponseEntity<PatientDto> partialUpDatePatientByDni(@PathVariable Long id,
                                                                @RequestBody Map<String,Object> updates){
        PatientDto updatedPatient=patientService.partialUpDatePatientById(id, updates);
        return ResponseEntity.ok(updatedPatient);
    }

    @Operation(summary = "Update  partially patient's information by Dni", description = "Update partially patient's information by dni")
    @PatchMapping("/update/dni/{dni}")
    public ResponseEntity<PatientDto> partialUpDatePatientByDni(@PathVariable String dni,
                                                                @RequestBody Map<String,Object> updates){
        PatientDto updatedPatient=patientService.partialUpDatePatientByDni(dni,updates );
        return ResponseEntity.ok(updatedPatient);
    }

    @Operation(summary = "Delete patient by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient with id "+id+" was deleted.");
    }
}
