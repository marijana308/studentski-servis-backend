package tseo.tseo19.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.tseo19.model.Enrollment;
import tseo.tseo19.model.ExamApplication;
import tseo.tseo19.model.ExamDateAndPlace;
import tseo.tseo19.repository.ExamDateAndPlaceRepository;

@Service
public class ExamDateAndPlaceService implements ExamDateAndPlaceServiceInterface {

	@Autowired
	private ExamDateAndPlaceRepository examDateAndPlaceRepository;

	@Override
	public ExamDateAndPlace save(ExamDateAndPlace examDateAndPlace) {
		return examDateAndPlaceRepository.save(examDateAndPlace);
	}

	@Override
	public List<ExamDateAndPlace> findAll() {
		return examDateAndPlaceRepository.findAll();
	}

	@Override
	public Optional<ExamDateAndPlace> findById(Long id) {
		return examDateAndPlaceRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		examDateAndPlaceRepository.deleteById(id);
	}
	
}
