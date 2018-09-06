package it.flyering.service;

import it.flyering.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user, String role);
}
