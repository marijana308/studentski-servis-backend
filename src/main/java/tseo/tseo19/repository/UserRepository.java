package tseo.tseo19.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tseo.tseo19.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUsername(String username);
}
