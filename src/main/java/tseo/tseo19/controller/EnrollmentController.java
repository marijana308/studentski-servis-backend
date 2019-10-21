package tseo.tseo19.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tseo.tseo19.dto.EnrollmentDTO;
import tseo.tseo19.model.Course;
import tseo.tseo19.model.Enrollment;
import tseo.tseo19.model.ExamApplication;
import tseo.tseo19.model.Student;
import tseo.tseo19.model.StudyProgramme;
import tseo.tseo19.model.Teacher;
import tseo.tseo19.model.Teaching;
import tseo.tseo19.model.User;
import tseo.tseo19.repository.UserRepository;
import tseo.tseo19.service.CourseService;
import tseo.tseo19.service.EnrollmentService;
import tseo.tseo19.service.StudentService;
import tseo.tseo19.service.TeacherService;

@RestController
@RequestMapping(value = "api/enrollments")
public class EnrollmentController {
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/grading/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<EnrollmentDTO>> getAllEnrollmentsForGrading(@PathVariable String username){
		Date currentDate = new Date();
		List<Enrollment> enrollmentsForGrading = new ArrayList<Enrollment>();
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Teacher teacher = teacherService.findByUserId(userID);
		Set<Teaching> teachersCourses = teacher.getCourses();
		for(Teaching t: teachersCourses) {
			Set<Enrollment> enrollments = t.getCourse().getEnrollments();
			for(Enrollment e: enrollments) {
				Set<ExamApplication> examApplications = e.getExamApplications();
				for(ExamApplication app: examApplications) {
					if(!app.getEnrollment().getCompleted()) { //currentDate.after(app.getExamDateAndPlace().getDate()) && 
						enrollmentsForGrading.add(app.getEnrollment());
					}
				}
			}
		}
		List<EnrollmentDTO> enrollmentDTOs = new ArrayList<EnrollmentDTO>();
		for(Enrollment e: enrollmentsForGrading) {
			enrollmentDTOs.add(new EnrollmentDTO(e));
		}
		return new ResponseEntity<>(enrollmentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/uncompleted/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<EnrollmentDTO>> getAllUncompletedEnrollments(@PathVariable String username){
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Student student = studentService.findByUserId(userID);
		List<Enrollment> enrollments = enrollmentService.findAllUncompleted(student.getId());
		List<EnrollmentDTO> enrollmentDTOs = new ArrayList<EnrollmentDTO>();
		for(Enrollment e: enrollments) {
			enrollmentDTOs.add(new EnrollmentDTO(e));
		}
		return new ResponseEntity<>(enrollmentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/completed/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<EnrollmentDTO>> getAllCompletedEnrollments(@PathVariable String username){
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Student student = studentService.findByUserId(userID);
		List<Enrollment> enrollments = enrollmentService.findAllCompleted(student.getId());
		List<EnrollmentDTO> enrollmentDTOs = new ArrayList<EnrollmentDTO>();
		for(Enrollment e: enrollments) {
			enrollmentDTOs.add(new EnrollmentDTO(e));
		}
		return new ResponseEntity<>(enrollmentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id){
		Enrollment enrollment  = enrollmentService.findById(id).orElse(null);
		if(enrollment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new EnrollmentDTO(enrollment), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<EnrollmentDTO> saveEnrollment(@RequestBody EnrollmentDTO enrollmentDTO){
		if (enrollmentDTO.getStudent() == null || enrollmentDTO.getCourse() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Course course = courseService.findById(enrollmentDTO.getCourse().getId()).orElse(null);
		Student student = studentService.findById(enrollmentDTO.getStudent().getId()).orElse(null);
		if(course == null || student == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Enrollment enrollment = new Enrollment();
		enrollment.setCompleted(false);
		enrollment.setCourse(course);
		enrollment.setStudent(student);
		enrollmentService.save(enrollment);
		return new ResponseEntity<>(new EnrollmentDTO(enrollment), HttpStatus.CREATED);
	}

	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<EnrollmentDTO> updateEnrollment(@RequestBody EnrollmentDTO enrollmentDTO){
		Optional<Enrollment> optional = enrollmentService.findById(enrollmentDTO.getId());
		Enrollment enrollment = optional.get();
		if (enrollment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		enrollment.setGrade(enrollmentDTO.getGrade());
		enrollment.setCompleted(true);
		
		enrollmentService.save(enrollment);
		
		return new ResponseEntity<>(new EnrollmentDTO(enrollment), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id){
		Enrollment enrollment = enrollmentService.findById(id).orElse(null);
		if(enrollment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		enrollmentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
