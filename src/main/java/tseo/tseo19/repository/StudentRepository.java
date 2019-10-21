package tseo.tseo19.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tseo.tseo19.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
