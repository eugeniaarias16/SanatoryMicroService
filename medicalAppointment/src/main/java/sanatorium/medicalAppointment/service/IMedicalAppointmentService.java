package sanatorium.medicalAppointment.service;

import sanatorium.medicalAppointment.dto.MedicalAppointmentRequestDto;
import sanatorium.medicalAppointment.dto.MedicalAppointmentResponseDto;

public interface IMedicalAppointmentService {

    MedicalAppointmentResponseDto createAppointment(MedicalAppointmentRequestDto requestDto);
    void deleteAppointmentById(Long id);

}
