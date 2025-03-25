package br.com.devcoelho.apirest.bank.model;

import br.com.devcoelho.apirest.bank.enums.AccountStatus;
import br.com.devcoelho.apirest.bank.enums.AccountType;
import br.com.devcoelho.apirest.bank.enums.TransactionType;
import br.com.devcoelho.apirest.bank.exceptions.InsufficientBalanceException;
import br.com.devcoelho.apirest.bank.exceptions.InvalidAccountException;
import br.com.devcoelho.apirest.bank.exceptions.InvalidOperationException;
import br.com.devcoelho.apirest.bank.model.interfaces.AccountInterface;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account implements AccountInterface {

  // Fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String accountNumber;

  private String agency;

  @Column(precision = 19, scale = 2)
  private BigDecimal balance;

  @Enumerated(EnumType.STRING)
  private AccountType accountType;

  @Enumerated(EnumType.STRING)
  private AccountStatus accountStatus;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Person client;

  @OneToMany(mappedBy = "sourceAccount", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Transaction> transactionHistory;

  private LocalDateTime openingDate;

  @OneToMany(mappedBy = "sourceAccount", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Transaction> transactions = new ArrayList<>();

  // Methods
  @Override
  public void deposit(BigDecimal value) {
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidOperationException("Deposit amount must be positive");
    }

    setBalance(getBalance().add(value));
  }

  @Override
  public void withdraw(BigDecimal value) {
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidOperationException("Withdraw amount must be positive");
    }

    if (value.compareTo(getBalance()) > 0) {
      throw new InsufficientBalanceException("Insufficient founds");
    }

    setBalance(getBalance().subtract(value));
  }

  @Override
  public boolean isValid() {
    return this.agency != null
        && this.agency.trim().length() > 0
        && this.client != null
        && this.accountNumber != null;
  }

  @Override
  public void printExtract() {
    this.toString();
  }

  public void transfer(BigDecimal value, Account accountOfDestiny) {
    if (!accountOfDestiny.isValid()) {
      throw new InvalidAccountException("Destination account is invalid");
    }

    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidOperationException("Transfer ammount must be positive");
    }

    if (value.compareTo(getBalance()) <= 0) {
      throw new InsufficientBalanceException("Insufficient balance to transfer");
    }

    this.withdraw(value);
    accountOfDestiny.deposit(value);

    Transaction lastTransaction = this.transactionHistory.get(this.transactionHistory.size() - 1);
    lastTransaction.setType(TransactionType.TRANSFER);
    lastTransaction.setDestinationAccount(accountOfDestiny);
  }

  @Override
  public void printTransactions() {
    if (transactionHistory.isEmpty()) {
      System.out.println("No transactions found");
      return;
    }

    for (Transaction transaction : transactionHistory) {
      System.out.println(transaction);
    }
  }
}
