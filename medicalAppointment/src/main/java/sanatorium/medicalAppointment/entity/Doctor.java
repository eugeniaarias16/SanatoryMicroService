package sanatorium.medicalAppointment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private Long id;
    private String firstName;
    private String lastName;
    private String medicalSpecialty;

}
