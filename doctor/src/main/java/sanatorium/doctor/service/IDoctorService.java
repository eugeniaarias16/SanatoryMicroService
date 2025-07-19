package sanatorium.doctor.service;

import sanatorium.doctor.dto.DoctorDto;

import java.util.List;
import java.util.Map;

public interface IDoctorService {
    List<DoctorDto> getAllDoctors();
    DoctorDto getDoctorById(Long id);
    DoctorDto getDoctorByLastName(String lastName);
    DoctorDto updateDoctorById(Long id,DoctorDto doctorDto);
    DoctorDto createDoctor(DoctorDto doctorDto);
    DoctorDto partialUpdateDoctorById(Long id, Map<String,Object> updates);
    DoctorDto partialUpdateDoctorByLastName(String lastName, Map<String,Object> updates);
    void deleteDoctor(Long id);
    DoctorDto getDoctorByFullNameAndSpecialty(String firstName, String lastName, String specialty);
    List<DoctorDto>getDoctorsBySpecialty(String specialty);
}
