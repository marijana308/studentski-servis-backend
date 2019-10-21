package tseo.tseo19.dto;

import tseo.tseo19.model.StudyProgramme;

public class StudyProgrammeDTO {
	
	private Long id;
	private String name;
	
	public StudyProgrammeDTO() {}

	public StudyProgrammeDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public StudyProgrammeDTO(StudyProgramme studyProgramme) {
		this(studyProgramme.getId(), studyProgramme.getName());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
