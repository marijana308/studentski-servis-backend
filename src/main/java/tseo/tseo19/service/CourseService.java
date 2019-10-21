package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.tseo19.model.Course;
import tseo.tseo19.repository.CourseRepository;

@Service
public class CourseService implements CourseServiceInterface {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course save(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@Override
	public Optional<Course> findById(Long id) {
		return courseRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		courseRepository.deleteById(id);
	}

}
