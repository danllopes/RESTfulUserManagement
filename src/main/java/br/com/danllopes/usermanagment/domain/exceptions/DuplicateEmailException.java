package br.com.danllopes.usermanagment.domain.exceptions;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String message) {super(message);}
}