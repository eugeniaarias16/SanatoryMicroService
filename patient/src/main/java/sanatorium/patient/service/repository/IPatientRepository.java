package sanatorium.patient.service.repository;

import sanatorium.patient.service.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IPatientRepository extends JpaRepository<Patient,Long> {

    Optional<Patient> findByDni(String dni);
}
