package sanatorium.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanatorium.doctor.entity.Doctor;

import java.util.Optional;

public interface IDoctorRepository  extends JpaRepository<Doctor,Long> {

    Optional<Doctor> findByLastName(String lastName);
}
