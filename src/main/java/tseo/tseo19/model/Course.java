package tseo.tseo19.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", unique=false, nullable=false)
	private String name;
	
	@Column(name="semester", unique=false, nullable=false)
	private String semester;
	
	@Column(name="year", unique=false, nullable=false)
	private String year;
	
	@Column(name="espb", unique=false, nullable=false)
	private String esbp;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private StudyProgramme studyProgramme;
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Teaching> teachers = new HashSet<Teaching>();
	
	@OneToMany(mappedBy="course", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<ExamDateAndPlace> examDatesAndPlaces = new HashSet<ExamDateAndPlace>();
	
	public Course() {
		super();
	}

	public Course(Long id, String name, String semester, String year, String espb, 
			StudyProgramme studyProgramme, Set<Enrollment> enrollments, 
			Set<Teaching> teachers, Set<ExamDateAndPlace> examDatesAndPlaces) {
		super();
		this.id = id;
		this.name = name;
		this.semester = semester;
		this.year = year;
		this.esbp = espb;
		this.studyProgramme = studyProgramme;
		this.enrollments = enrollments;
		this.teachers = teachers;
		this.examDatesAndPlaces = examDatesAndPlaces;
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

	public String getEsbp() {
		return esbp;
	}

	public void setEsbp(String esbp) {
		this.esbp = esbp;
	}

	public StudyProgramme getStudyProgramme() {
		return studyProgramme;
	}

	public void setStudyProgramme(StudyProgramme studyProgramme) {
		this.studyProgramme = studyProgramme;
	}

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public Set<Teaching> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teaching> teachers) {
		this.teachers = teachers;
	}

	public Set<ExamDateAndPlace> getExamDatesAndPlaces() {
		return examDatesAndPlaces;
	}

	public void setExamDatesAndPlaces(Set<ExamDateAndPlace> examDatesAndPlaces) {
		this.examDatesAndPlaces = examDatesAndPlaces;
	}

}
