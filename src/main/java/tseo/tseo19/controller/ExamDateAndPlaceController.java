package tseo.tseo19.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tseo.tseo19.dto.ExamDateAndPlaceDTO;
import tseo.tseo19.model.Course;
import tseo.tseo19.model.Enrollment;
import tseo.tseo19.model.ExamApplication;
import tseo.tseo19.model.ExamDateAndPlace;
import tseo.tseo19.model.ExamPeriod;
import tseo.tseo19.model.Student;
import tseo.tseo19.service.EnrollmentService;
import tseo.tseo19.service.ExamApplicationService;
import tseo.tseo19.service.ExamDateAndPlaceService;

@RestController
@RequestMapping(value = "api/examDatesAndPlaces")
public class ExamDateAndPlaceController {

	@Autowired
	private ExamDateAndPlaceService examDateAndPlaceService;
	
	@Autowired
	private ExamApplicationService examApplicationService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@RequestMapping(value = "/{enrollmentID}", method = RequestMethod.GET)
	public ResponseEntity<List<ExamDateAndPlaceDTO>> getExamDatesAndPlacesForEnrollment(@PathVariable Long enrollmentID) {
		//nadjemo enrollment sa id-em iz zahteva
		Enrollment enrollment = enrollmentService.findById(enrollmentID).orElse(null);
		Student student = enrollment.getStudent();
		//nadjemo predmet koji student pohadja
		Course course = enrollment.getCourse();
		//nadjemo sve datume za ispit za taj predmet koji student pohadja
		Set<ExamDateAndPlace> examDatesAndPlaces = course.getExamDatesAndPlaces();
		List<ExamDateAndPlace> examDatesAndPlacesForEnrollment = new ArrayList<ExamDateAndPlace>();
		Date currentDate = new Date();
		//proveravamo da li je istekao rok za prijavu ispita i da li je ispit vec prijavljen
		for(ExamDateAndPlace examDate: examDatesAndPlaces) {
			ExamPeriod examPeriod = examDate.getExamPeriod(); 
			if(currentDate.before(examPeriod.getStartDate())) {
				examDatesAndPlacesForEnrollment.add(examDate);
				for(ExamApplication application : examDate.getExamApplications()) {
					if(currentDate.before(application.getExamDateAndPlace().getDate()) && 
							application.getEnrollment().getStudent() == student) {
						examDatesAndPlacesForEnrollment.remove(examDate);
					}
					
				}
			}
		}
		List<ExamDateAndPlaceDTO> examDatesAndPlacesForEnrollmentDTOs = new ArrayList<ExamDateAndPlaceDTO>();
		for(ExamDateAndPlace eDandP: examDatesAndPlacesForEnrollment) {
			examDatesAndPlacesForEnrollmentDTOs.add(new ExamDateAndPlaceDTO(eDandP));
		}
		return new ResponseEntity<>(examDatesAndPlacesForEnrollmentDTOs, HttpStatus.OK);
	}
	
}
