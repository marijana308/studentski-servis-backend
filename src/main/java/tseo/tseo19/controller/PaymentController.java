package tseo.tseo19.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tseo.tseo19.dto.CourseDTO;
import tseo.tseo19.dto.PaymentDTO;
import tseo.tseo19.model.Course;
import tseo.tseo19.model.Payment;
import tseo.tseo19.model.Student;
import tseo.tseo19.model.User;
import tseo.tseo19.repository.UserRepository;
import tseo.tseo19.service.PaymentService;
import tseo.tseo19.service.StudentService;

@RestController
@RequestMapping(value = "api/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PaymentDTO>> getAllPayments(){
		List<Payment> payments = paymentService.findAll();
		List<PaymentDTO> paymentDTOs = new ArrayList<PaymentDTO>();
		for(Payment p: payments) {
			paymentDTOs.add(new PaymentDTO(p));
		}
		return new ResponseEntity<>(paymentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<PaymentDTO>> getAllPaymentsForStudent(@PathVariable String username){
		User user = userRepository.findByUsername(username);
		Long userID = user.getId();
		Student student = studentService.findByUserId(userID);
		List<Payment> payments = paymentService.findAllByStudentID(student.getId());
		List<PaymentDTO> paymentDTOs = new ArrayList<PaymentDTO>();
		for(Payment p: payments) {
			paymentDTOs.add(new PaymentDTO(p));
		}
		return new ResponseEntity<>(paymentDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PaymentDTO> savePayment(@RequestBody PaymentDTO paymentDTO){
		Payment payment = new Payment();
		payment.setAmount(paymentDTO.getAmount());
		payment.setStudent(studentService.findById(paymentDTO.getStudent().getId()).orElse(null));
		payment.setDate(new Date());
		
		paymentService.save(payment);
		
		return new ResponseEntity<>(new PaymentDTO(payment), HttpStatus.CREATED);
	}

}
