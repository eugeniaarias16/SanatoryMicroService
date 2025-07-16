package sanatorium.doctor.service;

import org.springframework.stereotype.Service;
import sanatorium.doctor.dto.DoctorDto;
import sanatorium.doctor.entity.Doctor;
import sanatorium.doctor.exceptions.ResourceNotFoundException;
import sanatorium.doctor.repository.IDoctorRepository;

@Service
public class DoctorService implements IDoctorService {

    private final IDoctorRepository doctorRepository;

    public DoctorService(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorDto getDoctorById(Long id) {
        Doctor doctor=doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor Not found with the id "+id));
        return new DoctorDto(doctor);

    }

    @Override
    public DoctorDto getDoctorByLastName(String lastName) {

        Doctor doctor=doctorRepository.findByLastName(lastName)
                .orElseThrow(()->new ResourceNotFoundException("Doctor Not found with last name "+lastName));
        return new DoctorDto(doctor);
    }

    @Override
    public DoctorDto updateDoctorById(Long id, DoctorDto doctorDto) {
        Doctor existingDoctor=doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor Not found with the id "+id));
        existingDoctor.setFirstName(doctorDto.firstName);
        existingDoctor.setLastName(doctorDto.lastName);
        existingDoctor.setMedicalSpecialty(doctorDto.medicalSpecialty);
        Doctor doctor=doctorRepository.save(existingDoctor);
        return new DoctorDto(doctor);
    }

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) {
        Doctor doctor=doctorDto.toEntityNoId();
        Doctor doctorSaved=doctorRepository.save(doctor);
        return new DoctorDto(doctorSaved);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
