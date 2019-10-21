package tseo.tseo19.dto;

import java.util.Date;

import tseo.tseo19.model.Student;

public class StudentDTO {

	private Long id;
	private String firstname;
	private String lastname;
	private String index;
	private StudyProgrammeDTO studyProgramme;
	private String semester;
	private String year;
	private String phone;
	private String email;
	private String address;
	private Date birthday;
	private String birthPlace;
	private Long paymentSum;

	public StudentDTO() {}

	public StudentDTO(Long id, String firstname, String lastname, String index, StudyProgrammeDTO studyProgramme, 
			String semester, String year, String phone, String email, String address, Date birthday, 
			String birthPlace, Long paymentSum) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.index = index;
		this.studyProgramme = studyProgramme;
		this.semester = semester;
		this.year = year;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.birthPlace = birthPlace;
		this.paymentSum = paymentSum;
	}

	public StudentDTO(Student student) {
		this(student.getId(), student.getFirstname(), student.getLastname(), student.getIndex(), 
				new StudyProgrammeDTO(student.getStudyProgramme()), student.getSemester(), student.getYear(), 
				student.getPhone(), student.getEmail(), student.getAddress(), student.getBirthday(), 
				student.getBirthPlace(), student.getPaymentSum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public StudyProgrammeDTO getStudyProgramme() {
		return studyProgramme;
	}

	public void setStudyProgramme(StudyProgrammeDTO studyProgramme) {
		this.studyProgramme = studyProgramme;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Long getPaymentSum() {
		return paymentSum;
	}

	public void setPaymentSum(Long paymentSum) {
		this.paymentSum = paymentSum;
	}
}
