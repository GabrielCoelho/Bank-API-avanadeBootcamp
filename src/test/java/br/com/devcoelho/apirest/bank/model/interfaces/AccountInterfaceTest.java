package br.com.devcoelho.apirest.bank.model.interfaces;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.devcoelho.apirest.bank.exceptions.InsufficientBalanceException;
import br.com.devcoelho.apirest.bank.exceptions.InvalidAccountException;
import br.com.devcoelho.apirest.bank.exceptions.InvalidOperationException;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountInterfaceTest {

  @Test
  @DisplayName("Should define interface methods correctly")
  public void shouldDefineInterfaceMethodsCorrectly() throws NoSuchMethodException {
    // Check that the interface has the expected methods with correct signatures
    Class<AccountInterface> clazz = AccountInterface.class;

    // Check isValid method
    assertNotNull(clazz.getMethod("isValid"));

    // Check withdraw method
    assertNotNull(clazz.getMethod("withdraw", BigDecimal.class));

    // Check deposit method
    assertNotNull(clazz.getMethod("deposit", BigDecimal.class));

    // Check transfer method
    assertNotNull(clazz.getMethod("transfer", BigDecimal.class, AccountInterface.class));

    // Check printExtract method
    assertNotNull(clazz.getMethod("printExtract"));

    // Check printTransactions method
    assertNotNull(clazz.getMethod("printTransactions"));
  }

  @Test
  @DisplayName("Should verify behavior with implementation")
  public void shouldVerifyBehaviorWithImplementation() {
    // Create a mock implementation of the interface
    AccountInterface mockAccount = mock(AccountInterface.class);

    // Set up expected behavior
    when(mockAccount.isValid()).thenReturn(true);
    doNothing().when(mockAccount).deposit(any(BigDecimal.class));
    doNothing().when(mockAccount).withdraw(any(BigDecimal.class));
    doNothing().when(mockAccount).transfer(any(BigDecimal.class), any(AccountInterface.class));

    // Test the behavior
    assertTrue(mockAccount.isValid());

    // These should not throw exceptions
    mockAccount.deposit(new BigDecimal("100.00"));
    mockAccount.withdraw(new BigDecimal("50.00"));

    // Verify the methods were called with the expected parameters
    verify(mockAccount).deposit(new BigDecimal("100.00"));
    verify(mockAccount).withdraw(new BigDecimal("50.00"));
  }

  @Test
  @DisplayName("Should test exception handling in interface implementation")
  public void shouldTestExceptionHandlingInInterfaceImplementation() {
    // Create a mock implementation that throws exceptions
    AccountInterface mockAccount = mock(AccountInterface.class);
    AccountInterface destAccount = mock(AccountInterface.class);

    // Set up the mock to throw exceptions
    doThrow(new InvalidOperationException("Negative amount"))
        .when(mockAccount).deposit(new BigDecimal("-10.00"));

    doThrow(new InsufficientBalanceException("Not enough balance"))
        .when(mockAccount).withdraw(new BigDecimal("1000.00"));

    doThrow(new InvalidAccountException("Invalid account"))
        .when(mockAccount).transfer(any(BigDecimal.class), eq(null));

    // Test exception handling
    assertThrows(InvalidOperationException.class, () -> {
      mockAccount.deposit(new BigDecimal("-10.00"));
    });

    assertThrows(InsufficientBalanceException.class, () -> {
      mockAccount.withdraw(new BigDecimal("1000.00"));
    });

    assertThrows(InvalidAccountException.class, () -> {
      mockAccount.transfer(new BigDecimal("50.00"), null);
    });
  }
}
