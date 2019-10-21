package tseo.tseo19.dto;

import java.util.Date;

import tseo.tseo19.model.ExamDateAndPlace;
import tseo.tseo19.model.ExamPeriod;
import tseo.tseo19.dto.ExamPeriodDTO;

public class ExamDateAndPlaceDTO {
	
	private Long id;
	
	private Date date;
	
	private String place;
	
	private ExamPeriodDTO examPeriod;

	public ExamDateAndPlaceDTO() {}

	public ExamDateAndPlaceDTO(Long id, Date date, String place, ExamPeriodDTO examPeriod) {
		super();
		this.id = id;
		this.date = date;
		this.place = place;
		this.examPeriod = examPeriod;
	}
	
	public ExamDateAndPlaceDTO(ExamDateAndPlace examDateAndPlace) {
		this(examDateAndPlace.getId(), examDateAndPlace.getDate(), examDateAndPlace.getPlace(),
				new ExamPeriodDTO(examDateAndPlace.getExamPeriod()));
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

	public ExamPeriodDTO getExamPeriod() {
		return examPeriod;
	}

	public void setExamPeriod(ExamPeriodDTO examPeriod) {
		this.examPeriod = examPeriod;
	}

}
