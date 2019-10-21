package tseo.tseo19.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tseo.tseo19.dto.ExamPeriodDTO;
import tseo.tseo19.dto.StudentDTO;
import tseo.tseo19.model.ExamPeriod;
import tseo.tseo19.model.Student;
import tseo.tseo19.model.User;
import tseo.tseo19.repository.UserRepository;
import tseo.tseo19.service.ExamPeriodService;

@RestController
@RequestMapping(value = "api/examPeriods")
public class ExamPeriodController {

	@Autowired
	private ExamPeriodService examPeriodService;
	
	@Autowired
	private UserRepository userRepository;
	
//	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
//	public ResponseEntity<ExamPeriodDTO> getExamPeriodsByUserID(@PathVariable String username) {
//		User user = userRepository.findByUsername(username);
//		Long userID = user.getId();
//		List<ExamPeriod> examPeriods = examPeriodService.findExamPeriodsByUserId(userID);
//		List<ExamPeriodDTO> examPeriodDTOs = new ArrayList<ExamPeriodDTO>();
//		for(ExamPeriod e: examPeriods) {
//			examPeriodDTOs.add(new ExamPeriodDTO(e));
//		}
//		return new ResponseEntity<>(examPeriodDTOs, HttpStatus.OK);
//	}
}
