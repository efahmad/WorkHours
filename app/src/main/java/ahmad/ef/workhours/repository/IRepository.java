package ahmad.ef.workhours.repository;

import java.util.List;

/**
 * Created by asma on 2/20/2015.
 */
public interface IRepository<T> {

    /**
     * Add a new entity
     * @param entity The entity object to be added
     */
    void add(T entity);

    /**
     * Get an entity by id
     * @param id Primary key of the entity
     * @return The entity with the specified id
     */
    T get(int id);

    /**
     * Get a list of all entities
     * @return List of entities
     */
    List<T> getAll();

    /**
     * Get count of entities
     * @return Count of entities
     */
    int getCount();

    /**
     * Update an entity
     * @param entity Data for the entity to be updated
     * @return Affected rows by the update command
     */
    int update(T entity);

    /**
     * Delete an entity
     * @param entity The entity object to be deleted
     */
    void delete(T entity);

    /**
     * Delete an entity by id
     * @param id Primary key of the entity to be deleted
     */
    void delete(int id);
}
