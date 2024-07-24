package br.com.danllopes.usermanagment.domain.exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String message) {super(message);}
}