package org.kshrd.services;

import java.util.List;

import org.kshrd.model.User;

public interface UserService {


	public List<User> findAll();
	public User search(String userhash);
	public boolean save(User user);
	public boolean deleteByUserHash(String userHash);
	public boolean updateByUserHash(User user);
	public boolean saveBatch(List<User> users);
	public int countTotal();
	public int countMale();
	public int countFemale();
	
}
