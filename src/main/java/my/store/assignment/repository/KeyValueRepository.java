package my.store.assignment.repository;

import java.util.Optional;

public interface KeyValueRepository {
    void set(String key, String value);

    Optional<String> get(String id);

    void deleteById(String id);
}
