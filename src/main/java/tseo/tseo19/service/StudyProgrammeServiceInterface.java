package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import tseo.tseo19.model.StudyProgramme;

public interface StudyProgrammeServiceInterface {
	
	List<StudyProgramme> findAll();
	
	Optional<StudyProgramme> findById(Long id);

}
