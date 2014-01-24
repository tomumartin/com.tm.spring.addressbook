package com.blizzard.addressbook.repository;

import com.blizzard.addressbook.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author twmartin
 * @since 1/23/14
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
