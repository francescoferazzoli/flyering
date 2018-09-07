package it.flyering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.flyering.dao.UserDAO;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserDAO, Long> {
	 UserDAO findByEmail(String email);
}
