package tseo.tseo19.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tseo.tseo19.dto.CourseDTO;
import tseo.tseo19.dto.EnrollmentDTO;
import tseo.tseo19.dto.ExamApplicationDTO;
import tseo.tseo19.model.Course;
import tseo.tseo19.model.Enrollment;
import tseo.tseo19.model.ExamApplication;
import tseo.tseo19.model.ExamDateAndPlace;
import tseo.tseo19.model.Student;
import tseo.tseo19.model.Teacher;
import tseo.tseo19.model.Teaching;
import tseo.tseo19.model.User;
import tseo.tseo19.repository.UserRepository;
import tseo.tseo19.service.EnrollmentService;
import tseo.tseo19.service.ExamApplicationService;
import tseo.tseo19.service.ExamDateAndPlaceService;
import tseo.tseo19.service.StudentService;
import tseo.tseo19.service.TeacherService;

@RestController
@RequestMapping(value = "api/examApplications")
public class ExamApplicationController {
	
	@Autowired
	private ExamApplicationService examApplicationService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private ExamDateAndPlaceService examDateAndPlaceService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/grading/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<ExamApplicationDTO>> getAllApplicationsForGrading(@PathVariable String username){
		Date currentDate = new Date();
		List<ExamApplication> applicationsForGrading = new ArrayList<ExamApplication>();
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Teacher teacher = teacherService.findByUserId(userID);
		Set<Teaching> teachersCourses = teacher.getCourses();
		for(Teaching t: teachersCourses) {
			Set<ExamDateAndPlace> examDatesAndPlaces = t.getCourse().getExamDatesAndPlaces();
			for(ExamDateAndPlace examDate: examDatesAndPlaces) {
//				Set<ExamApplication> examApplications = examDate.getExamApplications();
//				for(ExamApplication app: examApplications) {
//					applicationsForGrading.add(app);
//				}
				if(currentDate.after(examDate.getDate())) {
					Set<ExamApplication> examApplications = examDate.getExamApplications();
					for(ExamApplication app: examApplications) {
						if(app.getEnrollment().getCompleted().equals(false)) {
							applicationsForGrading.add(app);
						}
					}
				}
				
			}
		}
		List<ExamApplicationDTO> examApplicationDTOs = new ArrayList<ExamApplicationDTO>();
		for(ExamApplication e: applicationsForGrading) {
			examApplicationDTOs.add(new ExamApplicationDTO(e));
		}
		return new ResponseEntity<>(examApplicationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/courseApplications/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<ExamApplicationDTO>> findTeachersApplications (@PathVariable String username){
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Teacher teacher = teacherService.findByUserId(userID);
		List<ExamApplication> teachersApplications = examApplicationService.findTeachersApplications(teacher);
		List<ExamApplicationDTO> teachersApplicationDTOs = new ArrayList<ExamApplicationDTO>();
		for(ExamApplication app: teachersApplications) {
			teachersApplicationDTOs.add(new ExamApplicationDTO(app));
		}
		return new ResponseEntity<>(teachersApplicationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<ExamApplicationDTO>> findAllAppliedToExamsForStudent(@PathVariable String username){
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Student student = studentService.findByUserId(userID);
		List<Enrollment> enrollments = enrollmentService.findAllUncompleted(student.getId());
		List<ExamApplication> examApplications = examApplicationService.findAllAppliedToExamsForStudent(enrollments);
		List<ExamApplicationDTO> examApplicationDTOs = new ArrayList<ExamApplicationDTO>();
		for(ExamApplication e: examApplications) {
			examApplicationDTOs.add(new ExamApplicationDTO(e));
		}
		return new ResponseEntity<>(examApplicationDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ExamApplicationDTO> saveNewApplication(@RequestBody ExamApplicationDTO examApplicationDTO){
		System.out.println("saveNewApplication called, enrollment is: " + examApplicationDTO.getEnrollment().getCourse().getName());
		System.out.println("saveNewApplication called, examDateAndPlace is: " + examApplicationDTO.getExamDateAndPlace().getExamPeriod().getName());
		ExamApplication examApplication = new ExamApplication();
		examApplication.setEnrollment(enrollmentService.findById(examApplicationDTO.getEnrollment().getId()).orElse(null));
		examApplication.setExamDateAndPlace(examDateAndPlaceService.findById(examApplicationDTO.getExamDateAndPlace().getId()).orElse(null));
		
		examApplicationService.save(examApplication);
		
		return new ResponseEntity<>(new ExamApplicationDTO(examApplication), HttpStatus.CREATED);
	}

}
