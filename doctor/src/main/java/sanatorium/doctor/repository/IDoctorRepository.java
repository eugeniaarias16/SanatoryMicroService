package sanatorium.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanatorium.doctor.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface IDoctorRepository  extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByFirstNameAndLastNameAndMedicalSpecialty(String firstName, String lastName, String medicalSpecialty);
    Optional<Doctor> findByLastName(String lastName);
    Optional<List<Doctor>>findDoctorsByMedicalSpecialty(String medicalSpecialty);
}
