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
import javax.persistence.OneToMany;

@Entity
public class ExamPeriod {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "start_date", unique = false, nullable = false)
	private Date startDate;
	
	@Column(name = "end_date", unique = false, nullable = false)
	private Date endDate;
	
	@Column(name = "name", unique = false, nullable = false)
	private String name;
	
	@OneToMany(mappedBy="examPeriod", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<ExamDateAndPlace> examDatesAndPlaces = new HashSet<ExamDateAndPlace>();
//	
//	@OneToMany(mappedBy="examPeriod", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	private Set<ExamApplication> examApplications = new HashSet<ExamApplication>();

	public ExamPeriod() {}

	public ExamPeriod(Long id, Date startDate, Date endDate, String name, Set<ExamDateAndPlace> examDatesAndPlaces) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.name = name;
		this.examDatesAndPlaces = examDatesAndPlaces;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ExamDateAndPlace> getExamDatesAndPlaces() {
		return examDatesAndPlaces;
	}

	public void setExamDatesAndPlaces(Set<ExamDateAndPlace> examDatesAndPlaces) {
		this.examDatesAndPlaces = examDatesAndPlaces;
	}

}
