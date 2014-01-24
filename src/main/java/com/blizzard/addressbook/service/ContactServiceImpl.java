package com.blizzard.addressbook.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blizzard.addressbook.entity.Contact;
import com.blizzard.addressbook.exception.DataNotFound;
import com.blizzard.addressbook.repository.ContactRepository;

/**
 * @author twmartin
 * @since 1/23/14
 */
@NamedQuery(
		name = "findContactsByUsername",
		query = "call find_contacts_by_username(:username)")
@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private ContactRepository contactRepository;

	@Override
	public Contact create(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	@Transactional(rollbackFor = DataNotFound.class)
	public Contact delete(final int id) throws DataNotFound {
		Contact deletedUser = contactRepository.findOne(id);

		if (deletedUser == null) {
			throw new DataNotFound();
		}

		contactRepository.delete(id);
		return deletedUser;
	}

	@Override
	@Transactional(rollbackFor = DataNotFound.class)
	public Contact update(final Contact contact) throws DataNotFound {
		Contact updatedContact = contactRepository.findOne(contact.getId());

		if (updatedContact == null) {
			throw new DataNotFound();
		}

		updatedContact.setFirstName(contact.getFirstName());
		updatedContact.setLastName(contact.getLastName());
		updatedContact.setCompany(contact.getCompany());
		return updatedContact;
	}

	@Override
	public Contact findById(final int id) {
		return contactRepository.findOne(id);
	}
}
