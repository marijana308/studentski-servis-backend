package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import tseo.tseo19.model.Teacher;

public interface TeacherServiceInterface {

	Teacher save(Teacher teacher);
	
	List<Teacher> findAll();
	
	Teacher findByUserId(Long userID);
	
	Optional<Teacher> findById(Long id);
	
	void deleteById(Long id);
}
