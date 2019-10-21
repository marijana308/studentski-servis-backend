package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import tseo.tseo19.model.Course;

public interface CourseServiceInterface {
	
	Course save(Course course);
	
	List<Course> findAll();
	
	Optional<Course> findById(Long id);
	
	void deleteById(Long id);

}
