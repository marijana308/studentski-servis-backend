package tseo.tseo19.dto;

import java.util.Date;

import tseo.tseo19.model.ExamPeriod;

public class ExamPeriodDTO {
	
	private Long id;
	
	private Date startDate;
	
	private Date endDate;
	
	private String name;

	public ExamPeriodDTO() {}

	public ExamPeriodDTO(Long id, Date startDate, Date endDate, String name) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.name = name;
	}
	
	public ExamPeriodDTO(ExamPeriod examPeriod) {
		this(examPeriod.getId(), examPeriod.getStartDate(), examPeriod.getEndDate(), examPeriod.getName());
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

}
