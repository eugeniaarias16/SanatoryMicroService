package sanatorium.doctor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sanatorium.doctor.dto.DoctorDto;
import sanatorium.doctor.service.IDoctorService;

import java.util.List;
import java.util.Map;

@Tag(name = "DoctorController",description = "Operations related to doctor")
@RestController
@RequestMapping("api/doctor")

public class DoctorController {
    private final IDoctorService doctorService;

    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }


    public ResponseEntity<List<DoctorDto>>getAllDoctors(){
        List<DoctorDto>list=doctorService.getAllDoctors();
        return ResponseEntity.ok(list);
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


    @Operation(summary = "Get all doctors with a specific medicalSpecialty",description = "Get all doctors with a specific medicalSpecialty")
    @GetMapping("/specialty/{medicalSpecialty}")
    public ResponseEntity<List<DoctorDto>> getDoctorBySpecialty(@PathVariable String medicalSpecialty){
        List<DoctorDto>list=doctorService.getDoctorsBySpecialty(medicalSpecialty);
        return ResponseEntity.ok(list);
    }


    @Operation(summary = "Update doctor's full information.", description = "Update doctor's information with id.")
    @PutMapping("/id/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long id, @RequestBody DoctorDto doctorDto){
        DoctorDto doctor=doctorService.updateDoctorById(id,doctorDto);
        return ResponseEntity.ok(doctor);
    }


    @Operation(summary = "Update doctor's partial information.", description = "Update doctor's information with id.")
    @PatchMapping("/partialUpdate/id/{id}")
    public ResponseEntity<DoctorDto>partialUpdateDoctorById(@PathVariable Long id, @RequestBody Map<String,Object> updates){
        DoctorDto doctor=doctorService.partialUpdateDoctorById(id,updates);
        return ResponseEntity.ok(doctor);
    };

    @Operation(summary = "Update doctor's partial information.", description = "Update doctor's information with Last Name.")
    @PatchMapping("/partialUpdate/lastName/{lastName}")
    public ResponseEntity<DoctorDto>partialUpdateDoctorByLastName(@PathVariable String lastName, @RequestBody Map<String,Object> updates){
        DoctorDto doctor=doctorService.partialUpdateDoctorByLastName(lastName,updates);
        return ResponseEntity.ok(doctor);
    };

    @Operation(summary = "Create new Doctor.")
    @PostMapping("/create")
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody DoctorDto doctorDto){
        DoctorDto doctor=doctorService.createDoctor(doctorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }

    @Operation(summary = "Delete Doctor by id.")
    @DeleteMapping("/id/{id}")
    public  ResponseEntity<String> deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor was deleted successfully");
    }

}
