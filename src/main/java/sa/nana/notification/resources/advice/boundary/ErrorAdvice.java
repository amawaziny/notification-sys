package sa.nana.notification.resources.advice.boundary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sa.nana.notification.resources.advice.entity.ResponseMessage;

import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().
                stream().map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).
                collect(Collectors.joining(" | "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Arguments not valid " + message));
    }
}