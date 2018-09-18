package it.flyering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.flyering.dao.RoleDAO;

@Repository
public interface RoleRepository extends JpaRepository<RoleDAO, Integer> {
	RoleDAO findByRole(String role);

}
