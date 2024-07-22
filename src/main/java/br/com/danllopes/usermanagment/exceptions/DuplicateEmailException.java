package br.com.danllopes.usermanagment.exceptions;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String message) {super(message);}
}
