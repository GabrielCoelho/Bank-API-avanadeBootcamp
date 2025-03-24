package br.com.devcoelho.apirest.bank.model.interfaces;

import java.math.BigDecimal;

public interface Account {

  boolean isValid();

  void withdraw(BigDecimal value);

  void deposit(BigDecimal value);

  void transfer(BigDecimal value, Account accountOfDestiny);

  void printExtract();

  void printTransactions();
}
