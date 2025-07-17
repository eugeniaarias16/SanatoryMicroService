package sanatorium.patient.service.controller;

import sanatorium.patient.service.DTO.PatientDto;
import sanatorium.patient.service.service.IPatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto>getPatientById(@PathVariable Long id){
        PatientDto patientDto=patientService.getPatientById(id);
        return ResponseEntity.ok(patientDto);
    }
    @GetMapping("/dni/{dni}")
    public ResponseEntity<PatientDto>getPatientByDni(@PathVariable String dni){
        PatientDto patientDto=patientService.getPatientByDni(dni);
        return ResponseEntity.ok(patientDto);
    }

    @PostMapping("/create")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto){
        PatientDto createdPatient=patientService.createPatient(patientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<PatientDto> upDatePatientById(@PathVariable Long id,
                                                        @RequestBody PatientDto patientDto){
        PatientDto updatedPatient=patientService.updatePatientById(id, patientDto);
        return ResponseEntity.ok(updatedPatient);
    }

    @PutMapping("/updateByDni/{dni}")
    public ResponseEntity<PatientDto> upDatePatientByDni(@PathVariable String dni,
                                                        @RequestBody PatientDto patientDto){
        PatientDto updatedPatient=patientService.upDatePatientByDni(dni, patientDto);
        return ResponseEntity.ok(updatedPatient);
    }

    @PatchMapping("/update/id/{id}")
    public ResponseEntity<PatientDto> partialUpDatePatientById(@PathVariable Long id,
                                                        @RequestBody Map<String,Object> updates){
        PatientDto updatedPatient=patientService.partialUpDatePatientById(id, updates);
        return ResponseEntity.ok(updatedPatient);
    }

    @PatchMapping("/update/dni/{dni}")
    public ResponseEntity<PatientDto> partialUpDatePatientById(@PathVariable String dni,
                                                               @RequestBody Map<String,Object> updates){
        PatientDto updatedPatient=patientService.partialUpDatePatientByDni(dni,updates );
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient with id "+id+" was deleted.");
    }
}
