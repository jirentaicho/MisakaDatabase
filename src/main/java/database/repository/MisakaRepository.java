package database.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MisakaRepository<I,T> {

    /**
     * If you make any changes to the retrieved element,
     * you should copy it after retrieval and use it as a new object.
     *
     * @return
     */
    List<T> findAll();


    /**
     *
     * Returns elements in Optional
     *
     * If you make any changes to the retrieved element,
     * you should copy it after retrieval and use it as a new object.
     *
     * @param id
     * @return
     */
    Optional<T> findById(I id);

    boolean insert(I id,T obj);

    boolean insertMap(Map<I,T> map);

    boolean update(I id,T obj);

    boolean updateMap(Map<I,T> map);

    boolean deleteById(I id);

    boolean deleteAll();
}