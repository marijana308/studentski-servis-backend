package tseo.tseo19.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tseo.tseo19.model.Payment;
import tseo.tseo19.repository.PaymentRepository;

@Service
public class PaymentService implements PaymentServiceInterface {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}
	
	@Override
	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}
	
	@Override
	public List<Payment> findAllByStudentID(Long studentID) {
		List<Payment> allPayments = paymentRepository.findAll();
		List<Payment> studentsPayments = new ArrayList<Payment>();
		for(Payment p: allPayments) {
			if(p.getStudent().getId() == studentID) {
				studentsPayments.add(p);
			}
		}
		return studentsPayments;
	}

}
