package br.com.devcoelho.apirest.bank.model.interfaces;

import java.math.BigDecimal;

public interface AccountInterface {

  boolean isValid();

  void withdraw(BigDecimal value);

  void deposit(BigDecimal value);

  void transfer(BigDecimal value, AccountInterface accountOfDestiny);

  void printExtract();

  void printTransactions();
}
