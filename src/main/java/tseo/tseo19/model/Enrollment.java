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
public class Enrollment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "completed", unique = false, nullable = true)
	private Boolean completed;
	
	@Column(name = "grade", unique = false, nullable = true)
	private Long grade;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Student student;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Course course;
	
//	@OneToMany(mappedBy="enrollment", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	private Set<ExamDateAndPlace> examDatesAndPlaces = new HashSet<ExamDateAndPlace>();
	
	@OneToMany(mappedBy="enrollment", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<ExamApplication> examApplications = new HashSet<ExamApplication>();

	public Enrollment() {
		super();
	}

	public Enrollment(Long id, Boolean completed, Long grade, Student student, Course course,
			Set<ExamApplication> examApplications) {
		super();
		this.id = id;
		this.completed = completed;
		this.grade = grade;
		this.student = student;
		this.course = course;
		this.examApplications = examApplications;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Long getGrade() {
		return grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

	public Set<ExamApplication> getExamApplications() {
		return examApplications;
	}

	public void setExamApplications(Set<ExamApplication> examApplications) {
		this.examApplications = examApplications;
	}

}
