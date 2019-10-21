package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.tseo19.model.Student;
import tseo.tseo19.model.Teacher;
import tseo.tseo19.repository.TeacherRepository;

@Service
public class TeacherService implements TeacherServiceInterface {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public Teacher save(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}
	
	@Override
	public Teacher findByUserId(Long userID) {
		List<Teacher> teachers = teacherRepository.findAll();
		for(Teacher t: teachers) {
			if(t.getUser().getId().equals(userID)) {
				return t;
			}
		}
		return null;
	}

	@Override
	public Optional<Teacher> findById(Long id) {
		return teacherRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		teacherRepository.deleteById(id);
	}

}
