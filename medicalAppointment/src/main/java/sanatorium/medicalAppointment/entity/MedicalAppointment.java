package sanatorium.medicalAppointment.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MedicalAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime appointmentDateTime;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "doctor_id")),
            @AttributeOverride(name = "firstName", column = @Column(name = "doctor_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "doctor_last_name")),
            @AttributeOverride(name = "medicalSpecialty", column = @Column(name = "doctor_specialty"))
    })
    private Doctor doctor;
    @Embedded

    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "patient_id")),
            @AttributeOverride(name = "firstName", column = @Column(name = "patient_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "patient_last_name")),
            @AttributeOverride(name = "dni", column = @Column(name = "patient_dni"))
    })
    private Patient patient;

    public String getFormatterDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy 'at' HH:mm");
        String formatted = appointmentDateTime.format(formatter);
        return formatted;
    }

}
