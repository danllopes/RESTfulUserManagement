package br.com.danllopes.usermanagment.domain.exceptions;

public class LoginAlreadyExistsException extends RuntimeException{
    public LoginAlreadyExistsException(String message) {super(message);}
}