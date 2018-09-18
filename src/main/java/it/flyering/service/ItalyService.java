package it.flyering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import it.flyering.dao.CapDAO;
import it.flyering.dao.CityDAO;
import it.flyering.dao.ProvinceDAO;
import it.flyering.dao.RegionDAO;
import it.flyering.repository.CapRepository;
import it.flyering.repository.CityRepository;
import it.flyering.repository.ProvinceRepository;
import it.flyering.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItalyService {
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CapRepository capRepository;
	
	public RegionDAO getRegionById(int id) {
		return regionRepository.findOne(id);
	}
	
	@Cacheable(value = "porcodio")
	public List<RegionDAO> getAllRegions() {
		log.info("getAllRegions called");
		return regionRepository.findAll();
	}
	
	public List<ProvinceDAO> getAllProvincies() {
		return provinceRepository.findAll();
	}
	
	public List<CityDAO> getAllCities() {
		return cityRepository.findAll();
	}
	
	public CityDAO getCityByIstatCode(int istatCode) {
		return cityRepository.findOne(istatCode);
	}
	
	public List<CapDAO> getAllCaps() {
		return capRepository.findAll();
	}
	
	public CapDAO getCapById(int id) {
		return capRepository.findOne(id);
	}
	
	public List<CapDAO> getCapsByIstatCode(int istat) {
		CityDAO cityDAO = new CityDAO();
		cityDAO.setIstat(istat);
		return capRepository.findByCity(cityDAO);
	}

}
