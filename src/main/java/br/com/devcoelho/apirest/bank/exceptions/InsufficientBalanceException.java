package br.com.devcoelho.apirest.bank.exceptions;

public class InsufficientBalanceException extends RuntimeException {

  public InsufficientBalanceException(String message) {
    super(message);
  }
}
