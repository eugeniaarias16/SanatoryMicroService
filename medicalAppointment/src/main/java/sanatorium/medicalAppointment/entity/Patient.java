package sanatorium.medicalAppointment.entity;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private String  dni;
}
