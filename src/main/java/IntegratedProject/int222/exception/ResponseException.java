package IntegratedProject.int222.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseException {
    private String message;
    private LocalDateTime dateTime;
    private HttpStatus status;


    public ResponseException() {
        dateTime = LocalDateTime.now();
    }

    public ResponseException(HttpStatus status) {
        this();
        this.status = status;
    }

    public ResponseException(HttpStatus status,String message) {
        this();
        this.status = status;
        this.message = message;

    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
