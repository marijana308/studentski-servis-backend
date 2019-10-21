package tseo.tseo19.service;

import java.util.List;
import java.util.Optional;

import tseo.tseo19.model.Enrollment;
import tseo.tseo19.model.ExamApplication;
import tseo.tseo19.model.Teacher;

public interface ExamApplicationServiceInterface {
	
	ExamApplication save(ExamApplication examApplication);
	
	List<ExamApplication> findAll();
	
	List<ExamApplication> findAllAppliedToExamsForStudent(List<Enrollment> enrollments);
	
	List<ExamApplication> findTeachersApplications(Teacher teacher);
	
	Optional<ExamApplication> findById(Long id);
	
	void deleteById(Long id);

}
