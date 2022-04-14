package my.store.assignment.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.store.assignment.repository.KeyValueRepository;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class KeyValueRepositoryImpl implements KeyValueRepository {

    private static final String HASH_KEY_FORMAT = "store:%s";

    private final RedisOperations<String, String> redisOperations;

    @Override
    public void set(String key, String value) {
        redisOperations.opsForValue().set(getKey(key), value);
    }

    @Override
    public Optional<String> get(String id) {
        return Optional.ofNullable(redisOperations.opsForValue().get(getKey(id)));
    }

    @Override
    public void deleteById(String id) {
        redisOperations.delete(getKey(id));
    }

    private String getKey(String id) {
        return String.format(HASH_KEY_FORMAT, id);
    }
}
