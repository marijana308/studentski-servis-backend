package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import tseo.tseo19.model.Student;

public interface StudentServiceInterface {
	
	Student save(Student student);
	
	List<Student> findAll();
	
	Optional<Student> findById(Long id);
	
	Student findByUserId(Long userID);
	
	void deleteById(Long id);

}
