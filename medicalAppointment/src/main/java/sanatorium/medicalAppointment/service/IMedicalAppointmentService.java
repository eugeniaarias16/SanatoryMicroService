package sanatorium.medicalAppointment.service;

import sanatorium.medicalAppointment.dto.MedicalAppointmentRequestDto;
import sanatorium.medicalAppointment.dto.MedicalAppointmentResponseDto;

import java.util.List;

public interface IMedicalAppointmentService {

    MedicalAppointmentResponseDto createAppointment(MedicalAppointmentRequestDto requestDto);
    void deleteAppointmentById(Long id);
    List<MedicalAppointmentResponseDto> getAppointmentsByPatientDni(String dni);

}
