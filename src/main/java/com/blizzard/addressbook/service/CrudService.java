package com.blizzard.addressbook.service;

import com.blizzard.addressbook.exception.DataNotFound;

import java.util.List;

/**
 * @author twmartin
 * @since 1/23/14
 *
 * Basic CRUD operation interface for data.
 */
public interface CrudService<T> {
	public T create(final T data);

	public T delete(final int id) throws DataNotFound;

	public T update(final T data) throws DataNotFound;

	public T findById(final int id);
}
