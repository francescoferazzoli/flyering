package it.flyering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.flyering.dao.RegionDAO;

@Repository
public interface RegionRepository extends JpaRepository<RegionDAO, Integer> {
}
