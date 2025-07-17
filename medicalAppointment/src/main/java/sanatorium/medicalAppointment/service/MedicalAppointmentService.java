package sanatorium.medicalAppointment.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sanatorium.medicalAppointment.dto.MedicalAppointmentRequestDto;
import sanatorium.medicalAppointment.dto.MedicalAppointmentResponseDto;
import sanatorium.medicalAppointment.entity.Doctor;
import sanatorium.medicalAppointment.entity.MedicalAppointment;
import sanatorium.medicalAppointment.entity.Patient;
import sanatorium.medicalAppointment.repository.IMedicalAppointmentRepository;


@Service
public class MedicalAppointmentService implements IMedicalAppointmentService {

    private static final String PATIENT_URL="http://localhost:9001";
    private static final String DOCTOR_URL="http://localhost:9002";

    private final RestTemplate restTemplate;
    private final IMedicalAppointmentRepository medicalAppointmentRepository;
    public MedicalAppointmentService(RestTemplate restTemplate, IMedicalAppointmentRepository medicalAppointmentRepository) {
        this.restTemplate = restTemplate;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }



    @Override
    public MedicalAppointmentResponseDto createAppointment(MedicalAppointmentRequestDto requestDto) {
        //obtain patient data from ID card - consume customer api, searching by ID number
        String patientUrl=PATIENT_URL+"/api/patient/dni/"+requestDto.getPatientDni();
        Patient patient=restTemplate.getForObject(patientUrl,Patient.class);


        //obtain the patient object and request necessary data - id, firstname and lastname (dni already have)
        String doctorUrl = DOCTOR_URL + "/api/doctor?lastName=" + requestDto.getDoctorLastName() +
                "&firstName=" + requestDto.getDoctorFirstName() +
                "&medicalSpecialty=" + requestDto.getDoctorSpecialty();
        Doctor doctor=restTemplate.getForObject(doctorUrl,Doctor.class);

        //create the shift appointment with the data obtained from the apis and the date
        MedicalAppointment medicalAppointment=new MedicalAppointment();
        medicalAppointment.setAppointmentDateTime(requestDto.getAppointmentDateTime());
        medicalAppointment.setDoctor(doctor);
        medicalAppointment.setPatient(patient);

        //save in the DB
        medicalAppointmentRepository.save(medicalAppointment);
        //response with MedicalAppointmentResponseDto.
        return new MedicalAppointmentResponseDto(medicalAppointment);

    }

    @Override
    public void deleteAppointmentById(Long id) {
        medicalAppointmentRepository.deleteById(id);
    }
}
