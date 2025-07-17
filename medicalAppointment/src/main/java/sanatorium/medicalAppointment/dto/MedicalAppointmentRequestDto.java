package sanatorium.medicalAppointment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sanatorium.medicalAppointment.entity.MedicalAppointment;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalAppointmentRequestDto {

    private String patientDni;
    private String doctorFirstName;
    private String doctorLastName;
    private String doctorSpecialty;
    private String appointmentDate;
    private String appointmentTime;

    public LocalDateTime getAppointmentDateTime(){
        String dateTimeString=appointmentDate+" "+appointmentTime;
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(dateTimeString,formatter);
    }

}
