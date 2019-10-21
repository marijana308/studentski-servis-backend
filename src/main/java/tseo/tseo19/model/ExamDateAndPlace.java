package tseo.tseo19.model;

import java.util.Date;
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
public class ExamDateAndPlace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "date", nullable = false, unique = true)
	private Date date;
	
	@Column(name = "place", nullable = false, unique = true)
	private String place;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Course course;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private ExamPeriod examPeriod;

	@OneToMany(mappedBy="examDateAndPlace", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<ExamApplication> examApplications = new HashSet<ExamApplication>();

	public ExamDateAndPlace() {}
	
	public ExamDateAndPlace(Long id, Date date, String place, Course course, ExamPeriod examPeriod,
			Set<ExamApplication> examApplications) {
		super();
		this.id = id;
		this.date = date;
		this.place = place;
		this.course = course;
		this.examPeriod = examPeriod;
		this.examApplications = examApplications;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ExamPeriod getExamPeriod() {
		return examPeriod;
	}

	public void setExamPeriod(ExamPeriod examPeriod) {
		this.examPeriod = examPeriod;
	}

	public Set<ExamApplication> getExamApplications() {
		return examApplications;
	}

	public void setExamApplications(Set<ExamApplication> examApplications) {
		this.examApplications = examApplications;
	}

}
