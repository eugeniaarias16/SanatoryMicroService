package sanatorium.doctor.service;

import sanatorium.doctor.dto.DoctorDto;

public interface IDoctorService {

    DoctorDto getDoctorById(Long id);
    DoctorDto getDoctorByLastName(String lastName);
    DoctorDto updateDoctorById(Long id,DoctorDto doctorDto);
    DoctorDto createDoctor(DoctorDto doctorDto);
    void deleteDoctor(Long id);

}
