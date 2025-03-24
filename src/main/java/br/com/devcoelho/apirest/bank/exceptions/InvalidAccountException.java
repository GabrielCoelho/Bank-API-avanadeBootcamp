package br.com.devcoelho.apirest.bank.exceptions;

public class InvalidAccountException extends RuntimeException {

  public InvalidAccountException(String message) {
    super(message);
  }
}
