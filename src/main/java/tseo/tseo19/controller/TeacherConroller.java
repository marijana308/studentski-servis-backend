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

import tseo.tseo19.dto.TeacherDTO;
import tseo.tseo19.model.Teacher;
import tseo.tseo19.model.User;
import tseo.tseo19.repository.UserRepository;
import tseo.tseo19.service.TeacherService;

@RestController
@RequestMapping(value = "api/teachers")
public class TeacherConroller {

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TeacherDTO>> getAllTeachers(){
		List<Teacher> teachers = teacherService.findAll();
		List<TeacherDTO> teacherDTOs = new ArrayList<TeacherDTO>();
		for(Teacher t: teachers) {
			teacherDTOs.add(new TeacherDTO(t));
		}
		return new ResponseEntity<>(teacherDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{username}",method = RequestMethod.GET)
	public ResponseEntity<TeacherDTO> getTeacherByUserId(@PathVariable String username){
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Teacher teacher = teacherService.findByUserId(userID);
		System.out.print("teacher type is:" + teacher.getType());
		if(teacher == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new TeacherDTO(teacher), HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
//	public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id){
//		Teacher teacher = teacherService.findById(id).orElse(null);
//		if(teacher == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(new TeacherDTO(teacher), HttpStatus.OK);
//	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<TeacherDTO> saveTeacher(@RequestBody TeacherDTO teacherDTO){
		Teacher teacher = new Teacher();
		teacher.setFirstname(teacherDTO.getFirstname());
		teacher.setLastname(teacherDTO.getLastname());
		teacher.setType(teacherDTO.getType());
		
		teacherService.save(teacher);
		return new ResponseEntity<>(new TeacherDTO(teacher), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO){
		Teacher teacher = teacherService.findById(teacherDTO.getId()).orElse(null);
		if(teacher == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		teacher.setFirstname(teacherDTO.getFirstname());
		teacher.setLastname(teacherDTO.getLastname());
		teacher.setType(teacherDTO.getType());
		
		teacherService.save(teacher);
		return new ResponseEntity<>(new TeacherDTO(teacher), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTeacherById(@PathVariable Long id){
		Teacher teacher = teacherService.findById(id).orElse(null);
		if(teacher == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		teacherService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
