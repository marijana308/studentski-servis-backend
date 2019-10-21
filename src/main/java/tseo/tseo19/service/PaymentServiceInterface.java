package tseo.tseo19.service;

import java.util.List;

import tseo.tseo19.model.Payment;

public interface PaymentServiceInterface {
	
	Payment save(Payment payment);

	List<Payment> findAll();
	
	List<Payment> findAllByStudentID(Long studentID);
}
