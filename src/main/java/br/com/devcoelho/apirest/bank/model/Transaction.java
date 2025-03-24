package br.com.devcoelho.apirest.bank.model;

import br.com.devcoelho.apirest.bank.enums.TransactionType;
import br.com.devcoelho.apirest.bank.model.interfaces.AccountInterface;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long transactionId;

  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @Column(precision = 19, scale = 2)
  private BigDecimal amount;

  private Date date;

  @ManyToOne
  @JoinColumn(name = "source_account_id")
  private AccountInterface sourceAccount;

  @ManyToOne
  @JoinColumn(name = "destination_account_id")
  private AccountInterface destinationAccount;
}
