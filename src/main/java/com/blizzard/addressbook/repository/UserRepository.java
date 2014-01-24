package com.blizzard.addressbook.repository;

import com.blizzard.addressbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author twmartin
 * @since 1/21/14
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(final String username);
	public User findByUsernameAndPassword(final String username, final String password);
}
