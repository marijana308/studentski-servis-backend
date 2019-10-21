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
import javax.persistence.OneToMany;

@Entity
public class StudyProgramme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", unique=false, nullable=false)
	private String name;
	
	@OneToMany(mappedBy = "studyProgramme", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Course> courses = new HashSet<Course>();
	
	@OneToMany(mappedBy = "studyProgramme", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Student> students = new HashSet<Student>();
	
	public StudyProgramme() {}

	public StudyProgramme(Long id, String name, Set<Course> courses, Set<Student> students) {
		super();
		this.id = id;
		this.name = name;
		this.courses = courses;
		this.students = students;
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

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
