package br.com.danllopes.usermanagment.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {super(message);}
}
