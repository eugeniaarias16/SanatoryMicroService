package sanatorium.patient.service.service;

import sanatorium.patient.service.DTO.PatientDto;
import sanatorium.patient.service.entity.Patient;
import sanatorium.patient.service.exeptions.DuplicateFieldError;
import sanatorium.patient.service.exeptions.ResourceNotFoundException;
import sanatorium.patient.service.repository.IPatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class PatientService implements IPatientService{

    private final IPatientRepository patientRepository;

    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientDto getPatientById(Long id) {
        Patient patient=patientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Patient Not Found with id "+id));
        return new PatientDto(patient);

    }

    @Override
    public PatientDto getPatientByDni(String dni) {
        Patient patient=patientRepository.findByDni(dni)
                .orElseThrow(()-> new ResourceNotFoundException("Patient Not Found with dni "+dni));
        return new PatientDto(patient);
    }

    @Override
    public PatientDto createPatient(PatientDto patientDto) {

        //Validate if this dni does not already exist
        if(patientRepository.findByDni(patientDto.getDni()).isPresent()){
            throw new DuplicateFieldError("Patient with that DNI already exists.");
        }

        Patient patient=patientDto.toEntityNoId();
        Patient savedPatient=patientRepository.save(patient);
        return new PatientDto(savedPatient);
    }

    @Override
    public PatientDto updatePatientById(Long id, PatientDto patientDto) {
        Patient existingPatient=patientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Patient Not Found with id "+id));
        existingPatient.setFirstName(patientDto.getFirstName());
        existingPatient.setLastName(patientDto.getLastName());
        existingPatient.setDni(patientDto.getDni());
        existingPatient.setPhoneNumber(patientDto.getPhoneNumber());
        existingPatient.setBirthDate(patientDto.getBirthDate());

        Patient savedPatient=patientRepository.save(existingPatient);
        return new PatientDto(savedPatient);

    }

    @Override
    public PatientDto upDatePatientByDni(String dni, PatientDto patientDto) {
        Patient existingPatient=patientRepository.findByDni(dni)
                .orElseThrow(()-> new ResourceNotFoundException("Patient Not Found with dni "+dni));
        existingPatient.setFirstName(patientDto.getFirstName());
        existingPatient.setLastName(patientDto.getLastName());
        existingPatient.setDni(patientDto.getDni());
        existingPatient.setPhoneNumber(patientDto.getPhoneNumber());
        existingPatient.setBirthDate(patientDto.getBirthDate());
        Patient savedPatient=patientRepository.save(existingPatient);
        return new PatientDto(savedPatient);

    }

    public PatientDto partialUpDatePatientByDni(String dni, Map<String,Object>updates){
        Patient existingPatient=patientRepository.findByDni(dni)
                .orElseThrow(()-> new ResourceNotFoundException("Patient Not Found with dni "+dni));
        updates.forEach((key,value)->{
            switch (key){
                case "firstName"->existingPatient.setFirstName((String) value);
                case "lastName"->existingPatient.setLastName((String)value );
                case "dni"->existingPatient.setDni((String)value);
                case "phoneNumber"->existingPatient.setPhoneNumber((String) value);
                case "birthDate"->{
                    if(value instanceof String){
                        existingPatient.setBirthDate(LocalDate.parse((String)value));
                    } else if (value instanceof LocalDate) {
                        existingPatient.setBirthDate((LocalDate) value);
                    }
                }
                default -> System.out.println("Field not recognized: "+key);
            }
        });
        Patient savedPatient=patientRepository.save(existingPatient);
        return new PatientDto(savedPatient);
    }

    public PatientDto partialUpDatePatientById(Long id, Map<String,Object>updates){
        Patient existingPatient=patientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Patient Not Found with id "+id));
        updates.forEach((key,value)->{
            switch (key){
                case "firstName"->existingPatient.setFirstName((String) value);
                case "lastName"->existingPatient.setLastName((String)value );
                case "dni"->existingPatient.setDni((String)value);
                case "phoneNumber"->existingPatient.setPhoneNumber((String) value);
                case "birthDate"->{
                    if(value instanceof String){
                        existingPatient.setBirthDate(LocalDate.parse((String)value));
                    } else if (value instanceof LocalDate) {
                        existingPatient.setBirthDate((LocalDate) value);
                    }
                }
                default -> System.out.println("Field not recognized: "+key);
            }
        });
        Patient savedPatient=patientRepository.save(existingPatient);
        return new PatientDto(savedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
