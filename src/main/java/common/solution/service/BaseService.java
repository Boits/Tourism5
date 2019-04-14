package common.solution.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    void add(T entity);

    void add(Collection<T> items);

    Optional<T> findById(ID id);

//    T findById(ID id);

    void update(T entity);

    void deleteById(ID id);

    void delete(T entity);

    void printAll();

    List<T> getAll();
}
