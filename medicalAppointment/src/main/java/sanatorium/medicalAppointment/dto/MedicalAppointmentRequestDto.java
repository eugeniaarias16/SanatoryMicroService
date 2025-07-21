package sanatorium.medicalAppointment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalAppointmentRequestDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String patientDni;
    private String doctorFirstName;
    private String doctorLastName;
    private String doctorSpecialty;
    private String appointmentDate;
    private String appointmentTime;

    public LocalDateTime toLocalDateTime(){
        String dateTimeString=appointmentDate+" "+appointmentTime;
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(dateTimeString,formatter);
    }

}
