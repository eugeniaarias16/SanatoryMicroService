package sanatorium.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanatorium.doctor.entity.Doctor;

import java.util.Optional;

public interface IDoctorRepository  extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByFirstNameAndLastNameAndMedicalSpecialty(String firstName, String lastName, String specialty);
    Optional<Doctor> findByLastName(String lastName);
}
