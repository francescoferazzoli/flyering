package it.flyering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.flyering.dao.CityDAO;

public interface CityRepository extends JpaRepository<CityDAO, Integer>{

}
