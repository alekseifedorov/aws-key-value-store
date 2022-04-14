package my.store.assignment.service;

public interface KeyValueStoreService {

    void setValue(String key, String value);

    String getValue(String key);
}
