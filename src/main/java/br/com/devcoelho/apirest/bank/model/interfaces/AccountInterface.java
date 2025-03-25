package br.com.devcoelho.apirest.bank.model.interfaces;

import java.math.BigDecimal;

public interface AccountInterface {

  boolean isValid();

  void withdraw(BigDecimal value);

  void deposit(BigDecimal value);

  void printExtract();

  void printTransactions();
}
