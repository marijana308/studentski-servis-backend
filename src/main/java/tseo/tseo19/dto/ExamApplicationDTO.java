package tseo.tseo19.dto;

import tseo.tseo19.model.Enrollment;
import tseo.tseo19.model.ExamApplication;
import tseo.tseo19.model.ExamDateAndPlace;
import tseo.tseo19.dto.ExamDateAndPlaceDTO;

public class ExamApplicationDTO {

	private Long id;
	
	private EnrollmentDTO enrollment;
	
	private ExamDateAndPlaceDTO examDateAndPlace;

	public ExamApplicationDTO() {}

	public ExamApplicationDTO(Long id, EnrollmentDTO enrollment, ExamDateAndPlaceDTO examDateAndPlace) {
		super();
		this.id = id;
		this.enrollment = enrollment;
		this.examDateAndPlace = examDateAndPlace;
	}
	
	public ExamApplicationDTO(ExamApplication examApplication) {
		this(examApplication.getId(), new EnrollmentDTO(examApplication.getEnrollment()), 
				new ExamDateAndPlaceDTO(examApplication.getExamDateAndPlace()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnrollmentDTO getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(EnrollmentDTO enrollment) {
		this.enrollment = enrollment;
	}

	public ExamDateAndPlaceDTO getExamDateAndPlace() {
		return examDateAndPlace;
	}

	public void setExamDateAndPlace(ExamDateAndPlaceDTO examDateAndPlace) {
		this.examDateAndPlace = examDateAndPlace;
	}
	
}
