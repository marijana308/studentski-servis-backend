package tseo.tseo19.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.tseo19.model.Enrollment;
import tseo.tseo19.model.ExamApplication;
import tseo.tseo19.model.ExamDateAndPlace;
import tseo.tseo19.model.Teacher;
import tseo.tseo19.model.Teaching;
import tseo.tseo19.repository.ExamApplicationRepository;

@Service
public class ExamApplicationService implements ExamApplicationServiceInterface {

	@Autowired
	private ExamApplicationRepository examApplicationRepository;

	@Override
	public ExamApplication save(ExamApplication examApplication) {
		return examApplicationRepository.save(examApplication);
	}

	@Override
	public List<ExamApplication> findAll() {
		return examApplicationRepository.findAll();
	}
	
	@Override
	public List<ExamApplication> findAllAppliedToExamsForStudent(List<Enrollment> enrollments) {
		Date currentDate = new Date();
		List<ExamApplication> applied = new ArrayList<ExamApplication>();
		for(Enrollment e: enrollments) {
			Set<ExamApplication> applications = e.getExamApplications();
			for(ExamApplication app: applications) {
				ExamDateAndPlace examDateAndPlace = app.getExamDateAndPlace();
				if(currentDate.before(examDateAndPlace.getDate())) {
					applied.add(app);
				}
			}
		}
		return applied;
	}
	
	@Override
	public List<ExamApplication> findTeachersApplications(Teacher teacher) {
		Date currentDate = new Date();
		List<ExamApplication> allApplications = examApplicationRepository.findAll();
		List<ExamApplication> currentApplications = new ArrayList<ExamApplication>();
		List<ExamApplication> teachersApplications = new ArrayList<ExamApplication>();
		for(ExamApplication app: allApplications) {
			if(currentDate.before(app.getExamDateAndPlace().getDate())){
				currentApplications.add(app);
			}
		}
		for(ExamApplication app: currentApplications) {
			Set<Teaching> teachings = app.getEnrollment().getCourse().getTeachers();
			for (Teaching t: teachings) {
				if(t.getTeacher().equals(teacher)) {
					teachersApplications.add(app);
				}
			}
		}
		return teachersApplications;
	}

	@Override
	public Optional<ExamApplication> findById(Long id) {
		return examApplicationRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		examApplicationRepository.deleteById(id);
	}
	
}
