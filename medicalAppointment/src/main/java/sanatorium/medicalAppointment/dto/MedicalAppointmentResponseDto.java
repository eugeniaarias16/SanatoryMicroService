package sanatorium.medicalAppointment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sanatorium.medicalAppointment.entity.MedicalAppointment;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MedicalAppointmentResponseDto {

    private Long id;
    private String fullNamePatient;
    private String appointmentDate;
    private String fullNameDoctor;


    public MedicalAppointmentResponseDto(MedicalAppointment medicalAppointment){
        id=medicalAppointment.getId();
        fullNamePatient=medicalAppointment.getPatient().getFirstName()+" "+medicalAppointment.getPatient().getLastName();
        fullNameDoctor=medicalAppointment.getDoctor().getFirstName()+" "+medicalAppointment.getDoctor().getLastName();
        appointmentDate=medicalAppointment.getFormatterDate();
    }


    public String getFormattedMessage(){
        return String.format("Hello %s, your medical appointment with Dr. %s will take place %s.",
                fullNamePatient, fullNameDoctor, appointmentDate);
    }

}
