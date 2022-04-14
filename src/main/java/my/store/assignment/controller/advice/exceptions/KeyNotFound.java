package my.store.assignment.controller.advice.exceptions;

import lombok.Getter;

@Getter
public class KeyNotFound extends RuntimeException {
    public KeyNotFound(String msg) {
        super(msg);
    }
}
