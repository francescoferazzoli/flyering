package it.flyering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.flyering.dao.ProvinceDAO;

public interface ProvinceRepository extends JpaRepository<ProvinceDAO, String> {

}
