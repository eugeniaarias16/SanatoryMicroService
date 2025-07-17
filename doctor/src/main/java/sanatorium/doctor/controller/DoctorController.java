package sanatorium.doctor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanatorium.doctor.dto.DoctorDto;
import sanatorium.doctor.service.IDoctorService;

@Tag(name = "Doctor",description = "Operations related to doctor")
@RestController
@RequestMapping("api/doctor")
public class DoctorController {
    private final IDoctorService doctorService;

    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id){
        DoctorDto doctor=doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);

    }
    @GetMapping
    public ResponseEntity<DoctorDto> getDoctor(@RequestParam(required = false) String lastName,
                                               @RequestParam(required = false) String firstName,

                                               @RequestParam(required = false) String medicalSpecialty){

        if (lastName != null && firstName != null && medicalSpecialty != null) {
            // Search by the 3 parameters
            DoctorDto doctor = doctorService.getDoctorByFullNameAndSpecialty(firstName,lastName, medicalSpecialty);
            return ResponseEntity.ok(doctor);
        } else if (lastName != null) {
            // By last name only
            DoctorDto doctor = doctorService.getDoctorByLastName(lastName);
            return ResponseEntity.ok(doctor);
        }

        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Update doctor's information.")
    @PatchMapping("/id/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long id, @RequestBody DoctorDto doctorDto){
        DoctorDto doctor=doctorService.updateDoctorById(id,doctorDto);
        return ResponseEntity.ok(doctor);
    }

    @Operation(summary = "Create new Doctor.")
    @PutMapping("/create")
    public ResponseEntity<DoctorDto> createDoctor(DoctorDto doctorDto){
        DoctorDto doctor=doctorService.createDoctor(doctorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }

    @Operation(summary = "Delete Doctor by id.")
    @DeleteMapping("/id/{id}")
    public  ResponseEntity<String> deleteDoctor(Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor was deleted successfully");
    }

}
