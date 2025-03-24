package br.com.devcoelho.apirest.bank.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.devcoelho.apirest.bank.enums.TransactionType;
import br.com.devcoelho.apirest.bank.model.interfaces.Account;
import java.math.BigDecimal;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionTest {

  private Transaction transaction;

  @Mock private Account sourceAccountMock;

  @Mock private Account destinationAccountMock;

  private final long transactionId = 1L;
  private final BigDecimal amount = new BigDecimal("500.00");
  private final Date transactionDate = new Date();

  @BeforeEach
  public void setup() {
    transaction =
        new Transaction(
            1L,
            TransactionType.TRANSFER,
            new BigDecimal("100.00"),
            new Date(),
            sourceAccountMock,
            destinationAccountMock);
  }

  @Test
  @DisplayName("Should create transaction with all properties")
  public void shouldCreateTransactionWithAllProperties() {
    // Then
    assertEquals(transactionId, transaction.getTransactionId());
    assertEquals(TransactionType.TRANSFER, transaction.getType());
    assertEquals(amount, transaction.getAmount());
    assertEquals(transactionDate, transaction.getDate());
    assertEquals(sourceAccountMock, transaction.getSourceAccount());
    assertEquals(destinationAccountMock, transaction.getDestinationAccount());
  }

  @Test
  @DisplayName("Should update transaction type")
  public void shouldUpdateTransactionType() {
    // When
    transaction.setType(TransactionType.DEPOSIT);

    // Then
    assertEquals(TransactionType.DEPOSIT, transaction.getType());
  }

  @Test
  @DisplayName("Should update transaction amount")
  public void shouldUpdateTransactionAmount() {
    // Given
    BigDecimal newAmount = new BigDecimal("1000.00");

    // When
    transaction.setAmount(newAmount);

    // Then
    assertEquals(newAmount, transaction.getAmount());
  }

  @Test
  @DisplayName("Should update transaction date")
  public void shouldUpdateTransactionDate() {
    // Given
    Date newDate = new Date(transactionDate.getTime() + 86400000); // Add one day

    // When
    transaction.setDate(newDate);

    // Then
    assertEquals(newDate, transaction.getDate());
  }

  @Test
  @DisplayName("Should update source account")
  public void shouldUpdateSourceAccount() {
    // Given
    Account newSourceAccount = mock(Account.class);

    // When
    transaction.setSourceAccount(newSourceAccount);

    // Then
    assertEquals(newSourceAccount, transaction.getSourceAccount());
  }

  @Test
  @DisplayName("Should update destination account")
  public void shouldUpdateDestinationAccount() {
    // Given
    Account newDestinationAccount = mock(Account.class);

    // When
    transaction.setDestinationAccount(newDestinationAccount);

    // Then
    assertEquals(newDestinationAccount, transaction.getDestinationAccount());
  }

  @Test
  @DisplayName("ToString should include all important information")
  public void toStringShouldIncludeAllImportantInformation() {
    // When
    String result = transaction.toString();

    // Then
    assertTrue(result.contains(String.valueOf(transactionId)));
    assertTrue(result.contains(TransactionType.TRANSFER.name()));
    assertTrue(result.contains(amount.toString()));
  }

  @Test
  @DisplayName("Transaction with different types should behave correctly")
  public void transactionWithDifferentTypesShouldBehaveCorrectly() {
    Account nullAcc;
    // Given
    Transaction depositTransaction =
        new Transaction(
            2L,
            TransactionType.DEPOSIT,
            amount,
            transactionDate,
            sourceAccountMock,
            nullAcc // No destination account for deposit
            );

    Transaction withdrawalTransaction =
        new Transaction(
            3L,
            TransactionType.WITHDRAWAL,
            amount,
            transactionDate,
            sourceAccountMock,
            nullAcc // No destination account for withdrawal
            );

    // Then
    assertEquals(TransactionType.DEPOSIT, depositTransaction.getType());
    assertEquals(TransactionType.WITHDRAWAL, withdrawalTransaction.getType());
    assertNull(depositTransaction.getDestinationAccount());
    assertNull(withdrawalTransaction.getDestinationAccount());
  }
}
