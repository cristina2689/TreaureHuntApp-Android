package com.example.treasurehunt;

import com.example.treasurehunt.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "targetendpoint", namespace = @ApiNamespace(ownerDomain = "example.com", ownerName = "example.com", packagePath = "treasurehunt"))
public class TargetEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listTarget")
	public CollectionResponse<Target> listTarget(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Target> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Target as Target");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Target>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Target obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Target> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getTarget")
	public Target getTarget(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		Target target = null;
		try {
			target = mgr.find(Target.class, id);
		} finally {
			mgr.close();
		}
		return target;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param target the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertTarget")
	public Target insertTarget(Target target) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsTarget(target)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(target);
		} finally {
			mgr.close();
		}
		return target;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param target the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateTarget")
	public Target updateTarget(Target target) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsTarget(target)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(target);
		} finally {
			mgr.close();
		}
		return target;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 * @return The deleted entity.
	 */
	@ApiMethod(name = "removeTarget")
	public Target removeTarget(@Named("id") String id) {
		EntityManager mgr = getEntityManager();
		Target target = null;
		try {
			target = mgr.find(Target.class, id);
			mgr.remove(target);
		} finally {
			mgr.close();
		}
		return target;
	}

	private boolean containsTarget(Target target) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Target item = mgr.find(Target.class, target.getId());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
