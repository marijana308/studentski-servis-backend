package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.tseo19.model.StudyProgramme;
import tseo.tseo19.repository.StudyProgrammeRepository;

@Service
public class StudyProgrammeService implements StudyProgrammeServiceInterface{
	
	@Autowired
	private StudyProgrammeRepository repository;

	@Override
	public List<StudyProgramme> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<StudyProgramme> findById(Long id) {
		return repository.findById(id);
	}

}
