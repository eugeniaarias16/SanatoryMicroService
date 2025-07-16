package sanatorium.doctor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanatorium.doctor.dto.DoctorDto;
import sanatorium.doctor.service.IDoctorService;

@RestController
@RequestMapping("api/doctor")
public class DoctorController {
    private final IDoctorService doctorService;

    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id){
        DoctorDto doctor=doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);

    }

    public ResponseEntity<DoctorDto> getDoctorByLastName(@PathVariable String lastName){
        DoctorDto doctor=doctorService.getDoctorByLastName(lastName);
        return ResponseEntity.ok(doctor);

    }

    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long id, @RequestBody DoctorDto doctorDto){
        DoctorDto doctor=doctorService.updateDoctorById(id,doctorDto);
        return ResponseEntity.ok(doctor);
    }
    public ResponseEntity<DoctorDto> createDoctor(DoctorDto doctorDto){
        DoctorDto doctor=doctorService.createDoctor(doctorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }

    public  ResponseEntity<String> deleteDoctor(Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor was deleted successfully");
    }

}
