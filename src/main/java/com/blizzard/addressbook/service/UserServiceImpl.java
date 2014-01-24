package com.blizzard.addressbook.service;

import com.blizzard.addressbook.exception.DataNotFound;
import com.blizzard.addressbook.entity.User;
import com.blizzard.addressbook.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author twmartin
 * @since 1/21/14
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;

	@Override
	public User create(final User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional(rollbackFor=DataNotFound.class)
	public User delete(final int id) throws DataNotFound {
		User deletedUser = userRepository.findOne(id);

		if (deletedUser == null) {
			throw new DataNotFound();
		}

		userRepository.delete(id);
		return deletedUser;
	}

	@Override
	@Transactional(rollbackFor=DataNotFound.class)
	public User update(final User user) throws DataNotFound {
		User updatedUser = userRepository.findOne(user.getId());

		if (updatedUser == null) {
			throw new DataNotFound();
		}

		updatedUser.setUsername(user.getUsername());
		updatedUser.setPassword(user.getPassword());
		return updatedUser;
	}

	@Override
	public User findByUsername(final String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(final int id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
