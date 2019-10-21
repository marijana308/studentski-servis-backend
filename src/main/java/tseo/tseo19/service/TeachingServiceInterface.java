package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import tseo.tseo19.model.Teaching;

public interface TeachingServiceInterface {

	Teaching save(Teaching teaching);
	
	List<Teaching> findAll();
	
	List<Teaching> findAllByTeacherID(Long teacherID);
	
	Optional<Teaching> findById(Long id);
	
	void deleteById(Long id);
}
