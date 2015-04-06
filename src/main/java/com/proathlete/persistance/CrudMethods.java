package com.proathlete.persistance;


public interface CrudMethods<E> {

    /**
     * Returns an entity object from a backend datastore.
     * The id argument must specify an id value for an entity.
     * <p/>
     *
     * @param id an valid entity id
     * @return entity with the given id
     */
    E getById(Long id);

    /**
     * Persist a entity instance to the datastore
     *
     * @param entity the entity instance to persist
     * @return the updated state of the entity instance after being persisted
     */

    E save(E entity);

    /**
     * Delete a entity instance from the datastore
     *
     * @param entity the entity instance to delete
     * @return true if success, false if fail
     */
    boolean delete(E entity);
}
