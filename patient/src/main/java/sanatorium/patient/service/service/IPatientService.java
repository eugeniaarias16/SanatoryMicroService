package sanatorium.patient.service.service;

import sanatorium.patient.service.DTO.PatientDto;

import java.util.Map;

public interface IPatientService {

    PatientDto getPatientById(Long id);
    PatientDto getPatientByDni(String dni);
    PatientDto createPatient(PatientDto patientDto);
    PatientDto updatePatientById(Long id, PatientDto patientDto);
    PatientDto upDatePatientByDni(String dni,PatientDto patientDto);
    void deletePatient(Long id);
    PatientDto partialUpDatePatientByDni(String dni, Map<String,Object> updates);
    public PatientDto partialUpDatePatientById(Long id, Map<String,Object>updates);
}
