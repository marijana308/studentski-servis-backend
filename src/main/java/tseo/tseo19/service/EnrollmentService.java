package tseo.tseo19.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.tseo19.model.Enrollment;
import tseo.tseo19.repository.EnrollmentRepository;

@Service
public class EnrollmentService implements EnrollmentServiceInterface{

	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Override
	public Enrollment save(Enrollment enrollment) {
		return enrollmentRepository.save(enrollment);
	}

	@Override
	public List<Enrollment> findAll() {
		return enrollmentRepository.findAll();
	}
	
	@Override
	public List<Enrollment> findAllCompleted(Long studentID) {
		List<Enrollment> allEnrollments = enrollmentRepository.findAll();
		List<Enrollment> completed = new ArrayList<Enrollment>();
		for(Enrollment e: allEnrollments) {
			if(e.getCompleted().equals(true) && e.getStudent().getId() == studentID) {
				completed.add(e);
			}
		}
		return completed;
	}
	
	@Override
	public List<Enrollment> findAllUncompleted(Long studentID) {
		List<Enrollment> allEnrollments = enrollmentRepository.findAll();
		List<Enrollment> completed = new ArrayList<Enrollment>();
		for(Enrollment e: allEnrollments) {
			if(e.getCompleted().equals(false) && e.getStudent().getId() == studentID) {
				completed.add(e);
			}
		}
		return completed;
	}

	@Override
	public Optional<Enrollment> findById(Long id) {
		return enrollmentRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		enrollmentRepository.deleteById(id);
	}

}
