package br.com.danllopes.usermanagment.exceptions;

public class LoginAlreadyExistsException extends RuntimeException{
    public LoginAlreadyExistsException(String message) {super(message);}
}
