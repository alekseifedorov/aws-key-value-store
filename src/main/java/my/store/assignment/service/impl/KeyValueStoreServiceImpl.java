package my.store.assignment.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.store.assignment.controller.advice.exceptions.KeyNotFound;
import my.store.assignment.repository.KeyValueRepository;
import my.store.assignment.service.KeyValueStoreService;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class KeyValueStoreServiceImpl implements KeyValueStoreService {

    private final KeyValueRepository keyValueRepository;

    @Override
    public void setValue(String key, String value) {
        keyValueRepository.set(key, value);
    }

    @Override
    public String getValue(String key) {
        return keyValueRepository.get(key).orElseThrow(() -> new KeyNotFound("Not found key " + key));
    }
}
