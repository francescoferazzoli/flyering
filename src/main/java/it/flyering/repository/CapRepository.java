package it.flyering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.flyering.dao.CapDAO;
import it.flyering.dao.CityDAO;
import it.flyering.dao.UserDAO;

public interface CapRepository extends JpaRepository<CapDAO, Integer>{
	List<CapDAO> findByCity(CityDAO city);
}
