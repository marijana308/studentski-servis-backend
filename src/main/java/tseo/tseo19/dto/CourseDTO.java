package tseo.tseo19.dto;

import tseo.tseo19.model.Course;

public class CourseDTO {

	private Long id;
	private String name;
	private String semester;
	private String year;
	private String espb;
	private StudyProgrammeDTO studyProgramme;
	
	public CourseDTO() {}
	
	public CourseDTO(Long id, String name, String semester, String year, String espb, StudyProgrammeDTO studyProgramme) {
		super();
		this.id = id;
		this.name = name;
		this.semester = semester;
		this.year = year;
		this.espb = espb;
		this.studyProgramme = studyProgramme;
	}

	public CourseDTO(Course course) {
		this(course.getId(), course.getName(), course.getSemester(), course.getYear(), course.getEsbp(),
				new StudyProgrammeDTO(course.getStudyProgramme()));
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

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getEspb() {
		return espb;
	}

	public void setEspb(String espb) {
		this.espb = espb;
	}

	public StudyProgrammeDTO getStudyProgramme() {
		return studyProgramme;
	}

	public void setStudyProgramme(StudyProgrammeDTO studyProgramme) {
		this.studyProgramme = studyProgramme;
	}
	
}
