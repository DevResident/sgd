package fca.cifca.sgd.exception;

import static fca.cifca.sgd.util.ConstantesUtil.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalException {

    /**
     * Handle validation errors response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(Status, HttpStatus.BAD_REQUEST.value());
        body.put(Error, ErrorValidacion);

        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        body.put(Details, errors);

        log.warn("Error de validación: {}", errors);

        return ResponseEntity.badRequest().body(body);
    }

    /**
     * Handle runtime response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
        log.error("Error inesperado en la aplicación", ex);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(Status, HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put(Error, ErrorInesperado);
        body.put(Message, ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}