package sanatorium.doctor.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sanatorium.doctor.entity.Doctor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    
    public Long id;
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @NotBlank
    public String medicalSpecialty;

    public DoctorDto(Doctor doctor){
        id= doctor.getId();
        firstName=doctor.getFirstName();
        lastName= doctor.getLastName();
        medicalSpecialty=doctor.getMedicalSpecialty();

    }

    public Doctor toEntity(){
        Doctor doctor=new Doctor();
        doctor.setId(id);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setMedicalSpecialty(medicalSpecialty);
        return doctor;
    }

    public Doctor toEntityNoId(){
        Doctor doctor=new Doctor();
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setMedicalSpecialty(medicalSpecialty);
        return doctor;
    }


}
