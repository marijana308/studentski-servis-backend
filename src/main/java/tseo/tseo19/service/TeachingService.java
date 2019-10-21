package tseo.tseo19.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.tseo19.model.Teaching;
import tseo.tseo19.repository.TeachingRepository;

@Service
public class TeachingService implements TeachingServiceInterface {

	@Autowired
	private TeachingRepository teachingRepository;
	
	@Override
	public Teaching save(Teaching teaching) {
		return teachingRepository.save(teaching);
	}

	@Override
	public List<Teaching> findAll() {
		return teachingRepository.findAll();
	}

	@Override
	public List<Teaching> findAllByTeacherID(Long teacherID) {
		List<Teaching> allTeachings = teachingRepository.findAll();
		List<Teaching> usersTeachings = new ArrayList<Teaching>();
		for(Teaching t: allTeachings) {
			if(t.getTeacher().getId() == teacherID) {
				usersTeachings.add(t);
			}
		}
		return usersTeachings;
	}

	
	@Override
	public Optional<Teaching> findById(Long id) {
		return teachingRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		teachingRepository.deleteById(id);
	}

}
