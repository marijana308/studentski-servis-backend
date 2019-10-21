package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import tseo.tseo19.model.ExamPeriod;

public interface ExamPeriodServiceInterface {
	
	ExamPeriod save(ExamPeriod examPeriod);
	
	List<ExamPeriod> findAll();
	
	Optional<ExamPeriod> findById(Long id);
	
	void deleteById(Long id);

}
