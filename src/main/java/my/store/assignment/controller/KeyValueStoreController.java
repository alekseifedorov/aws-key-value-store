package my.store.assignment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import my.store.assignment.service.KeyValueStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class KeyValueStoreController {

    @Autowired
    private KeyValueStoreService keyValueStoreService;

    @PostMapping("/store/{key}/{value}")
    public void putValue(@PathVariable("key") String key, @PathVariable("value") String value) {
        log.info("Put value {} with key {}", key, value);
        keyValueStoreService.setValue(key, value);
    }

    @GetMapping("/store/{key}")
    public String getValue(@PathVariable("key") String key) {
        log.info("Get value by key {}", key);
        return keyValueStoreService.getValue(key);
    }
}
