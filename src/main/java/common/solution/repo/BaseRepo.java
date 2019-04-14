package common.solution.repo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseRepo<T, ID> {
    void add(T entity);

    void add(Collection<T> items);

    Optional<T> findById(ID id);

    void update(T entity);

    void deleteById(ID id);

    void printAll();

    List<T> getAll();

}
