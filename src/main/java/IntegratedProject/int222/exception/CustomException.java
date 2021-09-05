package IntegratedProject.int222.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {


    @ExceptionHandler(MessageException.class)
    public ResponseEntity<Object> handleExceptions(MessageException exception, WebRequest webRequest) {
        ResponseException response = new ResponseException(NOT_FOUND,exception.getMessage());
        return buildResponseEntity(response);
    }



    private ResponseEntity<Object> buildResponseEntity(ResponseException apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
