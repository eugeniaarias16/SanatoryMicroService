package sanatorium.medicalAppointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanatorium.medicalAppointment.entity.MedicalAppointment;

import java.util.List;

public interface IMedicalAppointmentRepository extends JpaRepository<MedicalAppointment,Long> {

   List<MedicalAppointment> findMedicalAppointmentsByPatientDni(String dni) ;

}
