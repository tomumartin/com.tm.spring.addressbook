package com.blizzard.addressbook.service;

import com.blizzard.addressbook.entity.User;

import java.util.List;

/**
 * @author twmartin
 * @since 1/21/14
 */
public interface UserService extends CrudService<User> {

	public User findByUsername(final String username);

	List<User> findAll();
}
