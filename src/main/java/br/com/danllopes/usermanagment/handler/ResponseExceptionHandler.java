package br.com.danllopes.usermanagment.handler;

import br.com.danllopes.usermanagment.exceptions.DuplicateEmailException;
import br.com.danllopes.usermanagment.exceptions.ExceptionResponse;
import br.com.danllopes.usermanagment.exceptions.LoginAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ExceptionResponse> handleDuplicateEmailException(Exception ex, WebRequest request) {
        ExceptionResponse response = this.buildExceptionResponse(ex, request, HttpStatus.CONFLICT);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LoginAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleLoginAlreadyExistsException(Exception ex, WebRequest request) {
        ExceptionResponse response = this.buildExceptionResponse(ex, request, HttpStatus.CONFLICT);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }


    private ExceptionResponse buildExceptionResponse(Exception ex, WebRequest request, HttpStatus status) {
        return new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), status);
    }
}
