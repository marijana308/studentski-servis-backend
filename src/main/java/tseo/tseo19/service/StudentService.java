package tseo.tseo19.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.tseo19.model.Student;
import tseo.tseo19.repository.StudentRepository;

@Service
public class StudentService implements StudentServiceInterface {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> findById(Long id) {
		return studentRepository.findById(id);
	}
	
	@Override
	public Student findByUserId(Long userID) {
		List<Student> students = studentRepository.findAll();
		for(Student s: students) {
			if(s.getUser().getId().equals(userID)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public void deleteById(Long id) {
		studentRepository.deleteById(id);
	}

}
