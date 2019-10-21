package tseo.tseo19.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tseo.tseo19.dto.StudyProgrammeDTO;
import tseo.tseo19.model.StudyProgramme;
import tseo.tseo19.service.StudyProgrammeService;

@RestController
@RequestMapping(value = "api/studyProgrammes")
public class StudyProgrammeController {
	
	@Autowired
	private StudyProgrammeService studyProgrammeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudyProgrammeDTO>> getAllStudyProgrammes(){
		List<StudyProgramme> studyProgrammes = studyProgrammeService.findAll();
		List<StudyProgrammeDTO> studyProgrammesDTOs = new ArrayList<StudyProgrammeDTO>();
		for(StudyProgramme s: studyProgrammes) {
			studyProgrammesDTOs.add(new StudyProgrammeDTO(s));
		}
		return new ResponseEntity<>(studyProgrammesDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<StudyProgrammeDTO> getStudyProgrammeById(@PathVariable Long id){
		StudyProgramme studyProgramme = studyProgrammeService.findById(id).orElse(null);
		if(studyProgramme == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new StudyProgrammeDTO(studyProgramme), HttpStatus.OK);
	}
	

}
