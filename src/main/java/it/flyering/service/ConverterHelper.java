package it.flyering.service;

import org.springframework.stereotype.Service;

import it.flyering.dao.UserDAO;
import it.flyering.model.User;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class ConverterHelper {
	
	MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();	
	
	public UserDAO convertUserToUserDAO(User user) {
		mapperFactory.classMap(User.class, UserDAO.class).byDefault();
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(user, UserDAO.class);
	}
}
