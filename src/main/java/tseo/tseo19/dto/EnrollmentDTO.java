package tseo.tseo19.dto;

import java.util.Date;

import tseo.tseo19.model.Enrollment;

public class EnrollmentDTO {
	
	private Long id;
	private Boolean completed;
	private Long grade;
	private StudentDTO student;
	private CourseDTO course;

	public EnrollmentDTO() {}

	public EnrollmentDTO(Long id, Boolean completed, Long grade, StudentDTO student, CourseDTO course) {
		super();
		this.id = id;
		this.completed = completed;
		this.grade = grade;
		this.student = student;
		this.course = course;
	}
	
	public EnrollmentDTO(Enrollment enrollment) {
		this(enrollment.getId(), enrollment.getCompleted(), enrollment.getGrade(), new StudentDTO(enrollment.getStudent()), 
				new CourseDTO(enrollment.getCourse()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Long getGrade() {
		return grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

}
