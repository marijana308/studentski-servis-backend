package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.tseo19.model.ExamDateAndPlace;
import tseo.tseo19.model.ExamPeriod;
import tseo.tseo19.model.Student;
import tseo.tseo19.repository.ExamPeriodRepository;

@Service
public class ExamPeriodService implements ExamPeriodServiceInterface {
	
	@Autowired
	private ExamPeriodRepository examPeriodRepository;

	@Override
	public ExamPeriod save(ExamPeriod examPeriod) {
		return examPeriodRepository.save(examPeriod);
	}

	@Override
	public List<ExamPeriod> findAll() {
		return examPeriodRepository.findAll();
	}
	
	@Override
	public Optional<ExamPeriod> findById(Long id) {
		return examPeriodRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		examPeriodRepository.deleteById(id);
	}

}
