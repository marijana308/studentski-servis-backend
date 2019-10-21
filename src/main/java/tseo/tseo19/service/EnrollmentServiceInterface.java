package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import tseo.tseo19.model.Enrollment;

public interface EnrollmentServiceInterface {
	
	Enrollment save(Enrollment enrollment);
	
	List<Enrollment> findAll();
	
	List<Enrollment> findAllCompleted(Long studentID);
	
	List<Enrollment> findAllUncompleted(Long studentID);
	
	Optional<Enrollment> findById(Long id);
	
	void deleteById(Long id);

}
