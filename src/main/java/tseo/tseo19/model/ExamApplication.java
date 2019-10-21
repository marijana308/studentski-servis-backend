package tseo.tseo19.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ExamApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Enrollment enrollment;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private ExamDateAndPlace examDateAndPlace;
	
	public ExamApplication() {}

	public ExamApplication(Long id, Enrollment enrollment, ExamDateAndPlace examDateAndPlace) {
		super();
		this.id = id;
		this.enrollment = enrollment;
		this.examDateAndPlace = examDateAndPlace;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	public ExamDateAndPlace getExamDateAndPlace() {
		return examDateAndPlace;
	}

	public void setExamDateAndPlace(ExamDateAndPlace examDateAndPlace) {
		this.examDateAndPlace = examDateAndPlace;
	}

}
