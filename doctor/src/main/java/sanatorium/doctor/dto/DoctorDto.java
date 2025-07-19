package sanatorium.doctor.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sanatorium.doctor.entity.Doctor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representation of a Doctor in the system")
public class DoctorDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotBlank
    @Schema(description = "Doctor's First Name",example = "Juan")
    private String firstName;
    @NotBlank
    @Schema(description = "Doctor's Last Name", example = "Perez")
    private String lastName;
    @NotBlank
    @Schema(description = "Doctor's medical speciality", example = "cardiology")
    private String medicalSpecialty;
    @Min(value = 1,message = "Salary cannot be lower than 1.")
    @Schema(description = "Doctor's mensual salary", example = "50000000")
    private Double salary;
    @Schema(description = "Doctor's address street",example = "Av Figueroa")
    private String address;
    @Schema(description = "Doctor's address number", example = "1234")
    private String numberAddress;
    @Size(min = 10, max = 15)
    @Schema(description = "Doctor's phone number in international format ",example = "+5491112345678")
    private String phoneNumber;

    public DoctorDto(Doctor doctor){
        id= doctor.getId();
        firstName=doctor.getFirstName();
        lastName= doctor.getLastName();
        medicalSpecialty=doctor.getMedicalSpecialty();
        salary= doctor.getSalary();
        address=doctor.getAddress();
        numberAddress=doctor.getNumberAddress();
        phoneNumber=doctor.getPhoneNumber();

    }

    public Doctor toEntity(){
        Doctor doctor=new Doctor();
        doctor.setId(id);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setMedicalSpecialty(medicalSpecialty);
        doctor.setAddress(address);
        doctor.setNumberAddress(numberAddress);
        doctor.setSalary(salary);
        doctor.setPhoneNumber(phoneNumber);
        return doctor;
    }

    public Doctor toEntityNoId(){
        Doctor doctor=new Doctor();
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setMedicalSpecialty(medicalSpecialty);
        doctor.setAddress(address);
        doctor.setNumberAddress(numberAddress);
        doctor.setSalary(salary);
        doctor.setPhoneNumber(phoneNumber);
        return doctor;
    }


}
