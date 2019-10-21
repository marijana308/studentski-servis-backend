package tseo.tseo19.dto;

import java.util.Date;

import tseo.tseo19.model.Payment;

public class PaymentDTO {
	
	private Long id;
	private Long amount;
	private Date date;
	private StudentDTO student;
	
	public PaymentDTO() {}
	
	public PaymentDTO(Long id, Long amount, Date date, StudentDTO student) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.student = student;
	}
	
	public PaymentDTO(Payment payment) {
		this(payment.getId(), payment.getAmount(), payment.getDate(), new StudentDTO(payment.getStudent()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

}
