package my.store.assignment.controller.advice;

import lombok.RequiredArgsConstructor;
import my.store.assignment.controller.advice.exceptions.KeyNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {

    @ExceptionHandler(KeyNotFound.class)
    public ResponseEntity<String> handle(KeyNotFound ex, WebRequest request, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
