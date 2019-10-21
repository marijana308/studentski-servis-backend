package tseo.tseo19.dto;

import java.util.Date;

import tseo.tseo19.model.Teacher;

public class TeacherDTO {
	
	private Long id;
	private String firstname;
	private String lastname;
	private String type;
	private String phone;
	private String email;
	private String address;
	private Date birthday;

	public TeacherDTO() {}

	public TeacherDTO(Long id, String firstname, String lastname, String type, String phone,
			String email, String address, Date birthday) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.type = type;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
	}
	
	public TeacherDTO(Teacher teacher) {
		this(teacher.getId(), teacher.getFirstname(), teacher.getLastname(), teacher.getType(),
				teacher.getPhone(), teacher.getEmail(), teacher.getAddress(), teacher.getBirthday());
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

}
