package br.com.devcoelho.apirest.bank.enums;

public enum TransactionType {
  DEPOSIT("Deposit"),
  WITHDRAWAL("Withdrawal"),
  TRANSFER("Transfer"),
  INTEREST("Interest Earned"),
  FEE("Account Fee");

  private final String description;

  TransactionType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
