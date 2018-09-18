package it.flyering;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import it.flyering.service.ItalyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableCaching
public class FlyeringApplication {
	
	@Autowired
	private ItalyService italyService;

	public static void main(String[] args) {
		SpringApplication.run(FlyeringApplication.class, args);
	}
	
	@PostConstruct
    public void init() {
		//https://github.com/gkatzioura/egkatzioura.wordpress.com/tree/master/SpringCachingWalkthrough
		//https://dzone.com/articles/spring-boot-with-ehcache-3-and-jsr-107
		
        log.info("----------------- LOADING CACHE STARTED -----------------");;
        log.info(italyService.getAllRegions().toString());
        log.info("----------------------------------");
        log.info(italyService.getAllRegions().toString());
//        log.info(italyService.getAllProvincies().toString());
//        log.info(italyService.getAllCities().toString());
//        log.info(italyService.getCityByIstatCode(1001).toString());
//        log.info(italyService.getAllCaps().toString());
//        log.info(italyService.getCapsByIstatCode(58091).toString());
        log.info("----------------- LOADING CACHE ENDED -----------------");;
    }

}
