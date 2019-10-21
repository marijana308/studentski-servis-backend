package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import tseo.tseo19.model.Enrollment;
import tseo.tseo19.model.ExamApplication;
import tseo.tseo19.model.ExamDateAndPlace;

public interface ExamDateAndPlaceServiceInterface {

	ExamDateAndPlace save(ExamDateAndPlace examDateAndPlace);
	
	List<ExamDateAndPlace> findAll();
	
	Optional<ExamDateAndPlace> findById(Long id);
	
	void deleteById(Long id);
}
