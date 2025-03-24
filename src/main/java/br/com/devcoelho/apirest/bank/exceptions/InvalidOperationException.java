package br.com.devcoelho.apirest.bank.exceptions;

public class InvalidOperationException extends RuntimeException {

  public InvalidOperationException(String message) {
    super(message);
  }
}
