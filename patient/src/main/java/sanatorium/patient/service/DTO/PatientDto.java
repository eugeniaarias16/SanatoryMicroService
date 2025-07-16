package sanatorium.patient.service.DTO;

import sanatorium.patient.service.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO representing a patient")
public class PatientDto {

    public Long id;
    @NotBlank
    @Size(min = 2, max = 70)
    public String firstName;
    @NotBlank
    @Size(min = 2, max = 70)
    public String lastName;
    @NotBlank
    @Size(min = 2, max = 20)
    @Schema(description = "Client's national ID number", example = "40123456")
    public String  dni;
    @NotBlank
    @Size(min = 10, max = 15)
    @Schema(description = "Patient's phone number in international format ",example = "+5491112345678")
    public String phoneNumber;

    @Past(message = "The date of birth must be earlier than today.")
    @NotNull
    @Schema(description = "Client's date of birth", example = "1990-05-20")
    public LocalDate birthDate;

    public PatientDto(Patient patient){
        if(patient !=null){
            this.id= patient.getId();
            this.firstName= patient.getFirstName();
            this.lastName= patient.getLastName();
            this.dni= patient.getDni();
            this.phoneNumber= patient.getPhoneNumber();
            this.birthDate= patient.getBirthDate();
        }
    }

    public Patient toEntity(){
        Patient patient =new Patient();
        patient.setBirthDate(birthDate);
        patient.setPhoneNumber(phoneNumber);
        patient.setDni(dni);
        patient.setLastName(lastName);
        patient.setFirstName(firstName);
        patient.setId(id);
        return patient;
    }
    public Patient toEntityNoId(){
        Patient patient =new Patient();
        patient.setBirthDate(birthDate);
        patient.setPhoneNumber(phoneNumber);
        patient.setDni(dni);
        patient.setLastName(lastName);
        patient.setFirstName(firstName);
        return patient;
    }
}
