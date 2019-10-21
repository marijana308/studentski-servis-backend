package tseo.tseo19.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tseo.tseo19.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
