package sanatorium.medicalAppointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanatorium.medicalAppointment.entity.MedicalAppointment;

public interface IMedicalAppointmentRepository extends JpaRepository<MedicalAppointment,Long> {
}
