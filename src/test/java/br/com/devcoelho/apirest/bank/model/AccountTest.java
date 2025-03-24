package br.com.devcoelho.apirest.bank.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.devcoelho.apirest.bank.enums.AccountStatus;
import br.com.devcoelho.apirest.bank.enums.AccountType;
import br.com.devcoelho.apirest.bank.enums.TransactionType;
import br.com.devcoelho.apirest.bank.exceptions.InsufficientBalanceException;
import br.com.devcoelho.apirest.bank.exceptions.InvalidAccountException;
import br.com.devcoelho.apirest.bank.exceptions.InvalidOperationException;
import br.com.devcoelho.apirest.bank.model.interfaces.AccountInterface;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

  private Account account;
  
  @Mock
  private Person clientMock;
  
  @Mock
  private AccountInterface destinationAccountMock;
  
  @BeforeEach
  public void setup() {
    account = new Account();
    account.setId(1L);
    account.setAccountNumber("123456");
    account.setAgency("0001");
    account.setBalance(new BigDecimal("1000.00"));
    account.setAccountType(AccountType.SIMPLE);
    account.setAccountStatus(AccountStatus.ACTIVE);
    account.setClient(clientMock);
    account.setOpeningDate(LocalDateTime.now());
    account.setTransactionHistory(new ArrayList<>());
    account.setTransactions(new ArrayList<>());
  }

  @Test
  @DisplayName("Should deposit amount successfully")
  public void depositShouldIncreaseBalance() {
    // Given
    BigDecimal initialBalance = account.getBalance();
    BigDecimal depositAmount = new BigDecimal("500.00");
    
    // When
    account.deposit(depositAmount);
    
    // Then
    assertEquals(initialBalance.add(depositAmount), account.getBalance());
  }

  @Test
  @DisplayName("Should throw exception when deposit amount is zero")
  public void depositShouldThrowExceptionWhenAmountIsZero() {
    // Given
    BigDecimal depositAmount = BigDecimal.ZERO;
    
    // Then
    assertThrows(InvalidOperationException.class, () -> {
      account.deposit(depositAmount);
    });
  }

  @Test
  @DisplayName("Should throw exception when deposit amount is negative")
  public void depositShouldThrowExceptionWhenAmountIsNegative() {
    // Given
    BigDecimal depositAmount = new BigDecimal("-100.00");
    
    // Then
    assertThrows(InvalidOperationException.class, () -> {
      account.deposit(depositAmount);
    });
  }

  @Test
  @DisplayName("Should withdraw amount successfully")
  public void withdrawShouldDecreaseBalance() {
    // Given
    BigDecimal initialBalance = account.getBalance();
    BigDecimal withdrawAmount = new BigDecimal("500.00");
    
    // When
    account.withdraw(withdrawAmount);
    
    // Then
    assertEquals(initialBalance.subtract(withdrawAmount), account.getBalance());
  }

  @Test
  @DisplayName("Should throw exception when withdraw amount is zero")
  public void withdrawShouldThrowExceptionWhenAmountIsZero() {
    // Given
    BigDecimal withdrawAmount = BigDecimal.ZERO;
    
    // Then
    assertThrows(InvalidOperationException.class, () -> {
      account.withdraw(withdrawAmount);
    });
  }

  @Test
  @DisplayName("Should throw exception when withdraw amount is negative")
  public void withdrawShouldThrowExceptionWhenAmountIsNegative() {
    // Given
    BigDecimal withdrawAmount = new BigDecimal("-100.00");
    
    // Then
    assertThrows(InvalidOperationException.class, () -> {
      account.withdraw(withdrawAmount);
    });
  }

  @Test
  @DisplayName("Should throw exception when withdraw amount exceeds balance")
  public void withdrawShouldThrowExceptionWhenAmountExceedsBalance() {
    // Given
    BigDecimal withdrawAmount = new BigDecimal("2000.00"); // Greater than the balance
    
    // Then
    assertThrows(InsufficientBalanceException.class, () -> {
      account.withdraw(withdrawAmount);
    });
  }

  @Test
  @DisplayName("Should validate account successfully")
  public void isValidShouldReturnTrueForValidAccount() {
    // Given - setup method already creates a valid account
    
    // When
    boolean isValid = account.isValid();
    
    // Then
    assertTrue(isValid);
  }

  @Test
  @DisplayName("Should return invalid when agency is null")
  public void isValidShouldReturnFalseWhenAgencyIsNull() {
    // Given
    account.setAgency(null);
    
    // When
    boolean isValid = account.isValid();
    
    // Then
    assertFalse(isValid);
  }

  @Test
  @DisplayName("Should return invalid when agency is empty")
  public void isValidShouldReturnFalseWhenAgencyIsEmpty() {
    // Given
    account.setAgency("   ");
    
    // When
    boolean isValid = account.isValid();
    
    // Then
    assertFalse(isValid);
  }

  @Test
  @DisplayName("Should return invalid when client is null")
  public void isValidShouldReturnFalseWhenClientIsNull() {
    // Given
    account.setClient(null);
    
    // When
    boolean isValid = account.isValid();
    
    // Then
    assertFalse(isValid);
  }

  @Test
  @DisplayName("Should return invalid when account number is null")
  public void isValidShouldReturnFalseWhenAccountNumberIsNull() {
    // Given
    account.setAccountNumber(null);
    
    // When
    boolean isValid = account.isValid();
    
    // Then
    assertFalse(isValid);
  }

  @Test
  @DisplayName("Should throw exception when transferring to invalid account")
  public void transferShouldThrowExceptionWhenDestinationAccountIsInvalid() {
    // Given
    BigDecimal transferAmount = new BigDecimal("100.00");
    
    // Mock behavior
    when(destinationAccountMock.isValid()).thenReturn(false);
    
    // Then
    assertThrows(InvalidAccountException.class, () -> {
      account.transfer(transferAmount, destinationAccountMock);
    });
  }

  @Test
  @DisplayName("Should throw exception when transfer amount is zero")
  public void transferShouldThrowExceptionWhenAmountIsZero() {
    // Given
    BigDecimal transferAmount = BigDecimal.ZERO;
    
    // Then
    assertThrows(InvalidOperationException.class, () -> {
      account.transfer(transferAmount, destinationAccountMock);
    });
  }

  @Test
  @DisplayName("Should throw exception when transfer amount is negative")
  public void transferShouldThrowExceptionWhenAmountIsNegative() {
    // Given
    BigDecimal transferAmount = new BigDecimal("-100.00");
    
    // Then
    assertThrows(InvalidOperationException.class, () -> {
      account.transfer(transferAmount, destinationAccountMock);
    });
  }

  @Test
  @DisplayName("Should throw exception when transfer amount exceeds balance")
  public void transferShouldThrowExceptionWhenAmountExceedsBalance() {
    // Given
    BigDecimal transferAmount = new BigDecimal("2000.00"); // Greater than balance
    
    // Mock behavior
    when(destinationAccountMock.isValid()).thenReturn(true);
    
    // Then
    assertThrows(InsufficientBalanceException.class, () -> {
      account.transfer(transferAmount, destinationAccountMock);
    });
  }

  @Test
  @DisplayName("Should handle no transactions when printing transactions")
  public void printTransactionsShouldHandleEmptyList() {
    // This is a void method that prints to console, so we're just ensuring it doesn't throw exceptions
    assertDoesNotThrow(() -> {
      account.printTransactions();
    });
  }
}
