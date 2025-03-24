package br.com.devcoelho.apirest.bank.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExceptionsTest {

  @Test
  @DisplayName("InsufficientBalanceException should store message correctly")
  public void insufficientBalanceExceptionShouldStoreMessageCorrectly() {
    // Given
    String errorMessage = "Not enough money in account";
    
    // When
    InsufficientBalanceException exception = new InsufficientBalanceException(errorMessage);
    
    // Then
    assertEquals(errorMessage, exception.getMessage());
  }

  @Test
  @DisplayName("InvalidAccountException should store message correctly")
  public void invalidAccountExceptionShouldStoreMessageCorrectly() {
    // Given
    String errorMessage = "Account is not valid";
    
    // When
    InvalidAccountException exception = new InvalidAccountException(errorMessage);
    
    // Then
    assertEquals(errorMessage, exception.getMessage());
  }

  @Test
  @DisplayName("InvalidOperationException should store message correctly")
  public void invalidOperationExceptionShouldStoreMessageCorrectly() {
    // Given
    String errorMessage = "Operation not allowed";
    
    // When
    InvalidOperationException exception = new InvalidOperationException(errorMessage);
    
    // Then
    assertEquals(errorMessage, exception.getMessage());
  }

  @Test
  @DisplayName("All exceptions should extend RuntimeException")
  public void allExceptionsShouldExtendRuntimeException() {
    // When
    InsufficientBalanceException insufficientBalanceException = 
        new InsufficientBalanceException("Test");
    InvalidAccountException invalidAccountException = 
        new InvalidAccountException("Test");
    InvalidOperationException invalidOperationException = 
        new InvalidOperationException("Test");
    
    // Then
    assertTrue(insufficientBalanceException instanceof RuntimeException);
    assertTrue(invalidAccountException instanceof RuntimeException);
    assertTrue(invalidOperationException instanceof RuntimeException);
  }

  @Test
  @DisplayName("Exceptions should be catchable as RuntimeException")
  public void exceptionsShouldBeCatchableAsRuntimeException() {
    // Then
    assertDoesNotThrow(() -> {
      try {
        throw new InsufficientBalanceException("Test");
      } catch (RuntimeException e) {
        // Should be caught here
        assertTrue(e instanceof InsufficientBalanceException);
      }
      
      try {
        throw new InvalidAccountException("Test");
      } catch (RuntimeException e) {
        // Should be caught here
        assertTrue(e instanceof InvalidAccountException);
      }
      
      try {
        throw new InvalidOperationException("Test");
      } catch (RuntimeException e) {
        // Should be caught here
        assertTrue(e instanceof InvalidOperationException);
      }
    });
  }
