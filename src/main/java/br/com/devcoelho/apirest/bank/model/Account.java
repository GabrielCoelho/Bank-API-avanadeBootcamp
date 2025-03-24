package br.com.devcoelho.apirest.bank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String accountNumber;

  private String agency;

  @Column(precision = 19, scale = 2)
  private BigDecimal balance;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Person client;

  private LocalDateTime openingDate;

  public void deposit(BigDecimal value) {
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      // @TODO jogar exeção de argumento ilegal
    }
    setBalance(getBalance().add(value));
  }

  public void withdraw(BigDecimal value) {
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      // @TODO jogar exeção de argumento ilegal
    }

    if (value.compareTo(getBalance()) > 0) {
      // @TODO jogar exceção de arguento ilegal
    }

    setBalance(getBalance().subtract(value));
  }
}
