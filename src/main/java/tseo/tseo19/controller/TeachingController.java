package tseo.tseo19.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tseo.tseo19.dto.TeachingDTO;
import tseo.tseo19.model.Course;
import tseo.tseo19.model.Teacher;
import tseo.tseo19.model.Teaching;
import tseo.tseo19.model.User;
import tseo.tseo19.repository.UserRepository;
import tseo.tseo19.service.CourseService;
import tseo.tseo19.service.TeacherService;
import tseo.tseo19.service.TeachingService;

@RestController
@RequestMapping(value = "api/teachings")
public class TeachingController {
	
	@Autowired
	private TeachingService teachingService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TeachingDTO>> getAllTeachings(){
		List<Teaching> teachings = teachingService.findAll();
		List<TeachingDTO> teachingDTOs = new ArrayList<TeachingDTO>();
		for(Teaching t: teachings) {
			teachingDTOs.add(new TeachingDTO(t));
		}
		return new ResponseEntity<>(teachingDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<TeachingDTO>> getAllTeachingsForUser(@PathVariable String username){
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Teacher teacher = teacherService.findByUserId(userID);
		List<Teaching> teachings = teachingService.findAllByTeacherID(teacher.getId());
		List<TeachingDTO> teachingDTOs = new ArrayList<TeachingDTO>();
		for(Teaching t: teachings) {
			teachingDTOs.add(new TeachingDTO(t));
		}
		return new ResponseEntity<>(teachingDTOs, HttpStatus.OK);
	}

//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<TeachingDTO> getTeachingById(@PathVariable Long id){
//		Teaching teaching = teachingService.findById(id).orElse(null);
//		if(teaching == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(new TeachingDTO(teaching), HttpStatus.OK);
//	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<TeachingDTO> saveTeaching(@RequestBody TeachingDTO teachingDTO){
		if(teachingDTO.getCourse() == null || teachingDTO.getTeacher() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Course course = courseService.findById(teachingDTO.getCourse().getId()).orElse(null);
		Teacher teacher = teacherService.findById(teachingDTO.getTeacher().getId()).orElse(null);
		if(course == null || teacher == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Teaching teaching = new Teaching();
		teaching.setCourse(course);
		teaching.setTeacher(teacher);
		teachingService.save(teaching);
		
		return new ResponseEntity<>(new TeachingDTO(teaching), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<TeachingDTO> updateTeaching(@RequestBody TeachingDTO teachingDTO){
		Teaching teaching = teachingService.findById(teachingDTO.getId()).orElse(null);
		if(teaching == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		teaching.setCourse(courseService.findById(teachingDTO.getCourse().getId()).orElse(null));
		teaching.setTeacher(teacherService.findById(teachingDTO.getTeacher().getId()).orElse(null));
		teachingService.save(teaching);
		
		return new ResponseEntity<>(new TeachingDTO(teaching), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTeachingById(@PathVariable Long id){
		Teaching teaching = teachingService.findById(id).orElse(null);
		if(teaching == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		teachingService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
