package sanatorium.doctor.service;

import org.springframework.stereotype.Service;
import sanatorium.doctor.dto.DoctorDto;
import sanatorium.doctor.entity.Doctor;
import sanatorium.doctor.exceptions.ResourceNotFoundException;
import sanatorium.doctor.repository.IDoctorRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.Deflater;

@Service
public class DoctorService implements IDoctorService {

    private final IDoctorRepository doctorRepository;

    public DoctorService(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor>allDoctors= doctorRepository.findAll();
        if(allDoctors.isEmpty()){
            throw new ResourceNotFoundException("There is no doctor loaded in the system.");
        }
        return allDoctors.stream()
                .map(DoctorDto::new)
                .collect(Collectors.toList());
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
    public DoctorDto getDoctorByFullNameAndSpecialty(String firstName, String lastName, String medicalSpecialty) {

        Doctor doctor=doctorRepository.findByFirstNameAndLastNameAndMedicalSpecialty(firstName,lastName,medicalSpecialty)
                .orElseThrow(()->new ResourceNotFoundException("Doctor with that information was not found."));

        return new DoctorDto(doctor);
    }

    @Override
    public List<DoctorDto> getDoctorsBySpecialty(String medicalSpecialty) {
        List<Doctor>list=doctorRepository.findDoctorsByMedicalSpecialty(medicalSpecialty)
                .orElseThrow(()->new ResourceNotFoundException("Doctors not found with the medicalSpecialty "+medicalSpecialty));
        return list.stream()
                .map(DoctorDto::new)
                .collect(Collectors.toList());
    }


    @Override
    public DoctorDto updateDoctorById(Long id, DoctorDto doctorDto) {
        Doctor existingDoctor=doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor Not found with the id "+id));
        existingDoctor.setFirstName(doctorDto.getFirstName());
        existingDoctor.setLastName(doctorDto.getLastName());
        existingDoctor.setMedicalSpecialty(doctorDto.getMedicalSpecialty());
        existingDoctor.setSalary(doctorDto.getSalary());
        existingDoctor.setAddress(doctorDto.getAddress());
        existingDoctor.setNumberAddress(doctorDto.getNumberAddress());
        existingDoctor.setPhoneNumber(doctorDto.getPhoneNumber());
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
    public DoctorDto partialUpdateDoctorById(Long id, Map<String,Object> updates) {
        Doctor existingDoctor= doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor with id "+id+" doesn't exist."));
        updates.forEach((key,value)->{
            switch (key){
                case "firstName"-> existingDoctor.setFirstName((String) value);
                case "lastName"-> existingDoctor.setLastName((String) value);
                case "salary"->{
                    if(value instanceof Number){
                        existingDoctor.setSalary(((Number)value).doubleValue());
                    } else if (value instanceof String) {
                        existingDoctor.setSalary(Double.parseDouble((String) value));
                    }
                }
                case"address"-> existingDoctor.setAddress((String) value);
                case "numberAddress"-> existingDoctor.setNumberAddress((String) value);
                case "phoneNumber"->existingDoctor.setPhoneNumber((String) value);
                case "medicalSpecialty"->existingDoctor.setMedicalSpecialty((String) value);
                default -> System.out.println("Field not recognized: "+key);
            }
        });
        Doctor doctor=doctorRepository.save(existingDoctor);
        return new DoctorDto(doctor);
    }

    @Override
    public DoctorDto partialUpdateDoctorByLastName(String lastName, Map<String,Object> updates) {
        Doctor existingDoctor=doctorRepository.findByLastName(lastName)
                .orElseThrow(()-> new ResourceNotFoundException("Doctor "+lastName+" doesn't exist."));
        updates.forEach((key,value)->{
            switch (key){
                case "firstName"-> existingDoctor.setFirstName((String) value);
                case "lastName"-> existingDoctor.setLastName((String) value);
                case "salary"->{
                    if(value instanceof Number){
                        existingDoctor.setSalary(((Number)value).doubleValue());
                    } else if (value instanceof String) {
                        existingDoctor.setSalary(Double.parseDouble((String) value));
                    }
                }
                case"address"-> existingDoctor.setAddress((String) value);
                case "numberAddress"-> existingDoctor.setNumberAddress((String) value);
                case "phoneNumber"->existingDoctor.setPhoneNumber((String) value);
                case "medicalSpecialty"->existingDoctor.setMedicalSpecialty((String) value);
                default -> System.out.println("Field not recognized: "+key);
            }
        });
        Doctor doctor=doctorRepository.save(existingDoctor);
        return new DoctorDto(doctor);
    }


    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }


}
