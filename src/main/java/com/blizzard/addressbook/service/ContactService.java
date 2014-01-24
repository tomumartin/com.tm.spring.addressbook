package com.blizzard.addressbook.service;

import com.blizzard.addressbook.entity.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author twmartin
 * @since 1/21/14
 */
@Service
public interface ContactService extends CrudService<Contact> {
}
