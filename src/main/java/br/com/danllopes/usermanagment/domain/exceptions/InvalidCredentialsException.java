package br.com.danllopes.usermanagment.domain.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String message) {super(message);}
}