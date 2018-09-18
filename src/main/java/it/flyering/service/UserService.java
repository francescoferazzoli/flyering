package it.flyering.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.flyering.dao.RoleDAO;
import it.flyering.dao.UserDAO;
import it.flyering.repository.RoleRepository;
import it.flyering.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserDAO findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void saveUser(UserDAO user, String role) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        RoleDAO userRole = roleRepository.findByRole(role);
        user.setRoles(new HashSet<RoleDAO>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	public void updatePassword(UserDAO user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
}
