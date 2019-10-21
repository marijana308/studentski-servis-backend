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

import tseo.tseo19.dto.CourseDTO;
import tseo.tseo19.dto.StudyProgrammeDTO;
import tseo.tseo19.model.Course;
import tseo.tseo19.model.StudyProgramme;
import tseo.tseo19.service.CourseService;
import tseo.tseo19.service.StudyProgrammeService;

@RestController
@RequestMapping(value = "api/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudyProgrammeService studyProgrammeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CourseDTO>> getAllCourses(){
		List<Course> courses = courseService.findAll();
		List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();
		for(Course c: courses) {
			courseDTOs.add(new CourseDTO(c));
		}
		return new ResponseEntity<>(courseDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/studyProgrammes", method = RequestMethod.GET)
	public ResponseEntity<List<StudyProgrammeDTO>> getAllStudyProgrammes(){
		List<StudyProgramme> programmes = studyProgrammeService.findAll();
		List<StudyProgrammeDTO> programmeDTOs = new ArrayList<StudyProgrammeDTO>();
		for(StudyProgramme s: programmes) {
			programmeDTOs.add(new StudyProgrammeDTO(s));
		}
		return new ResponseEntity<>(programmeDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<CourseDTO>> getCourseByStudyProgrammeId(@PathVariable Long id){
		StudyProgramme programme = studyProgrammeService.findById(id).orElse(null);
		if(programme == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Course> courses = courseService.findAll();
		List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();
		for(Course c: courses) {
			if(c.getStudyProgramme().getId() == id) {
				courseDTOs.add(new CourseDTO(c));
			}
		}
		return new ResponseEntity<>(courseDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO courseDTO){
		Course course = new Course();
		course.setName(courseDTO.getName());
		
		courseService.save(course);
		
		return new ResponseEntity<>(new CourseDTO(course), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO){
		Course course = courseService.findById(courseDTO.getId()).orElse(null);
		if (course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		course.setName(courseDTO.getName());
		
		courseService.save(course);
		
		return new ResponseEntity<>(new CourseDTO(course), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCourseById(@PathVariable Long id){
		Course course = courseService.findById(id).orElse(null);
		if(course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			courseService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
