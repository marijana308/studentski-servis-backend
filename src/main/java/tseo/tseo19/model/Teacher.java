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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="firstname", unique=false, nullable=false)
	private String firstname;
	
	@Column(name="lastname", unique=false, nullable=false)
	private String lastname;
	
	@Column(name="type", unique=false, nullable=false)
	private String type;
	
	@Column(name="phone", unique=false, nullable=false)
	private String phone;
	
	@Column(name="email", unique=false, nullable=false)
	private String email;
	
	@Column(name="address", unique=false, nullable=false)
	private String address;
	
	@Column(name="birthday", unique=false, nullable=false)
	private Date birthday;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	@OneToMany(mappedBy="teacher", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Teaching> courses = new HashSet<Teaching>();

	public Teacher() {
		super();
	}

	public Teacher(Long id, String firstname, String lastname, String type, String phone, String email, String address,
			Date birthday, User user, Set<Teaching> courses) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.type = type;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
		this.user = user;
		this.courses = courses;
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

	public Set<Teaching> getCourses() {
		return courses;
	}

	public void setCourses(Set<Teaching> courses) {
		this.courses = courses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
