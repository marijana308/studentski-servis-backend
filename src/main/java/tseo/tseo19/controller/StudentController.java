package tseo.tseo19.controller;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tseo.tseo19.dto.StudentDTO;
import tseo.tseo19.dto.StudyProgrammeDTO;
import tseo.tseo19.model.Authority;
import tseo.tseo19.model.Enrollment;
import tseo.tseo19.model.Payment;
import tseo.tseo19.model.Student;
import tseo.tseo19.model.StudyProgramme;
import tseo.tseo19.model.User;
import tseo.tseo19.repository.UserRepository;
import tseo.tseo19.service.StudentService;
import tseo.tseo19.service.StudyProgrammeService;

@RestController
@RequestMapping(value = "api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudyProgrammeService studyProgrammeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getAllStudents(){
		List<Student> students = studentService.findAll();
		List<StudentDTO> studentDTOs = new ArrayList<StudentDTO>();
		for(Student s: students) {
			studentDTOs.add(new StudentDTO(s));
		}
		return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<StudentDTO> getStudentByUserID(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Student student = studentService.findByUserId(userID);
		//System.out.println("GET STUDENT BY USERNAME CALLED, STUDENT IS:" + student.getFirstname() + " sum: " + student.getPaymentSum());
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/decrease/{username}", method = RequestMethod.GET)
	public ResponseEntity<StudentDTO> decreasePaymentSumForStudent(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Student student = studentService.findByUserId(userID);
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
//		Long sum = (long) 0;
//		for(Payment payment : student.getPayments()) {
//			sum = sum + payment.getAmount();
//		}
		student.setPaymentSum(student.getPaymentSum() - 200);
		
		studentService.save(student);
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/averageGrade/{username}", method = RequestMethod.GET)
	public ResponseEntity<Double> getAverageGrade(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Student student = studentService.findByUserId(userID);
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Set<Enrollment> all = student.getEnrollments();
		Double gradeSum = (double) 0;
		Double numberOfGrades = (double) 0;
		for(Enrollment e: all) {
			if(e.getCompleted()) {
				gradeSum = gradeSum + e.getGrade();
				numberOfGrades = numberOfGrades + 1;
			}
		}
		Double averageGrade = gradeSum / numberOfGrades;
		return new ResponseEntity<>(averageGrade, HttpStatus.OK);
	}
	
//	public String generateIndex(StudyProgramme programme) {
//		String indeks = "indeks";
//		if(programme.getName().equals("Softverske i informacione tehnologije")) {
//			int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);
//			int year = Year.now().getValue();
//			indeks = "SF" + randomNum + "/" + year;
//		}
//		if(programme.getName().equals("Graficko inzenjerstvo i dizajn")) {
//			int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);
//			int year = Year.now().getValue();
//			indeks = "GI" + randomNum + "/" + year;
//		}
//		if(programme.getName().equals("Arhitektura")) {
//			int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);
//			int year = Year.now().getValue();
//			indeks = "AU" + randomNum + "/" + year;
//		}
//		return indeks;
//	}
	
//	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
//	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
//		Student student = new Student();
//		StudyProgramme programme = studyProgrammeService.findById(studentDTO.getStudyProgramme().getId()).orElse(null);
//		student.setFirstname(studentDTO.getFirstname());
//		student.setLastname(studentDTO.getLastname());
//		student.setAddress(studentDTO.getAddress());
//		student.setBirthday(studentDTO.getBirthday());
//		student.setBirthPlace(studentDTO.getBirthPlace());
//		student.setEmail(studentDTO.getEmail());
//		student.setPhone(studentDTO.getPhone());
//		student.setSemester("1");
//		student.setYear("1");
//		student.setStudyProgramme(programme);
//		String indeks = generateIndex(programme);
//		student.setIndex(indeks);
//		student.setPaymentSum((long) 0);
//		
//		User user = new User();
//		List<Authority> roles = new ArrayList<Authority>();
//		roles.add(e)
//		
//		studentService.save(student);
//		
//		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.CREATED);
//	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO){
		Student student = studentService.findById(studentDTO.getId()).orElse(null);
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		student.setFirstname(studentDTO.getFirstname());
		student.setLastname(studentDTO.getLastname());
		studentService.save(student);
		
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
		Student student = studentService.findById(id).orElse(null);
		if(student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		studentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
