package ua.vbielslyi.amazon.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.vbielslyi.amazon.exception.ReportNotFoundException;
import ua.vbielslyi.amazon.exception.UserAlreadyExistException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<String> handleUserAlreadyExist(UserAlreadyExistException ex) {
        return new ResponseEntity<>("Already exist", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UsernameNotFoundException ex) {
        return new ResponseEntity<>("Not found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>("Bad credentials.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<String> handleReportNotFoundException(ReportNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
