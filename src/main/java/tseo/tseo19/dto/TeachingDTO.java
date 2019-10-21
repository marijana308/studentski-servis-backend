package tseo.tseo19.dto;

import tseo.tseo19.model.Teaching;

public class TeachingDTO {

	private Long id;
	
	private TeacherDTO teacher;
	
	private CourseDTO course;

	public TeachingDTO() {}

	public TeachingDTO(Long id, TeacherDTO teacher, CourseDTO course) {
		super();
		this.id = id;
		this.teacher = teacher;
		this.course = course;
	}

	public TeachingDTO(Teaching teaching) {
		this(teaching.getId(), new TeacherDTO(teaching.getTeacher()), new CourseDTO(teaching.getCourse()));
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TeacherDTO getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherDTO teacher) {
		this.teacher = teacher;
	}

	public CourseDTO getCourse() {
		return course;
	}

	public void setCourse(CourseDTO course) {
		this.course = course;
	}
}
