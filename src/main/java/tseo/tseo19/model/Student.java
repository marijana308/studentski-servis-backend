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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="firstname", unique=false, nullable=false)
	private String firstname;
	
	@Column(name="lastname", unique=false, nullable=false)
	private String lastname;
	
	@Column(name="indeks", unique=false, nullable=false)
	private String index;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private StudyProgramme studyProgramme;
	
	@Column(name="semester", unique=false, nullable=false)
	private String semester;
	
	@Column(name="year", unique=false, nullable=false)
	private String year;
	
	@Column(name="phone", unique=false, nullable=false)
	private String phone;
	
	@Column(name="email", unique=false, nullable=false)
	private String email;
	
	@Column(name="address", unique=false, nullable=false)
	private String address;
	
	@Column(name="birthday", unique=false, nullable=false)
	private Date birthday;
	
	@Column(name="birth_place", unique=false, nullable=false)
	private String birthPlace;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	@OneToMany(mappedBy="student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();
	
	@OneToMany(mappedBy="student", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Payment> payments = new HashSet<Payment>();
	
	@Column(name="payment_sum", unique=false, nullable=false)
	private Long paymentSum;

	public Student() {
		super();
	}

	public Student(Long id, String firstname, String lastname, String index, StudyProgramme studyProgramme, String semester, String year,
			String phone, String email, String address, Date birthday, String birthPlace, User user, Set<Enrollment> enrollments, 
			Set<Payment> payments, Long paymentSum) {
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
		this.user = user;
		this.enrollments = enrollments;
		this.payments = payments;
		this.paymentSum = paymentSum;
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

	public StudyProgramme getStudyProgramme() {
		return studyProgramme;
	}

	public void setStudyProgramme(StudyProgramme studyProgramme) {
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

	public Set<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public Long getPaymentSum() {
		return paymentSum;
	}

	public void setPaymentSum(Long paymentSum) {
		this.paymentSum = paymentSum;
	}
	
}
