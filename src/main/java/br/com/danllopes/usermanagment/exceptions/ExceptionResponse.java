package br.com.danllopes.usermanagment.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDate;

public record ExceptionResponse(LocalDate timestamp, String message, String details,
                                HttpStatus status) implements Serializable {

}
