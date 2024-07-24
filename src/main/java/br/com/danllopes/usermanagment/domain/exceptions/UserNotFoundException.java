package br.com.danllopes.usermanagment.domain.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {super(message);}
}