package br.com.devcoelho.apirest.bank.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountStatusTest {

  @Test
  @DisplayName("Should have correct number of account statuses")
  public void shouldHaveCorrectNumberOfAccountStatuses() {
    assertEquals(3, AccountStatus.values().length);
  }

  @Test
  @DisplayName("Should contain ACTIVE status")
  public void shouldContainActiveStatus() {
    // When
    boolean containsActive = false;
    for (AccountStatus status : AccountStatus.values()) {
      if (status == AccountStatus.ACTIVE) {
        containsActive = true;
        break;
      }
    }

    // Then
    assertTrue(containsActive);
  }

  @Test
  @DisplayName("Should contain BLOCKED status")
  public void shouldContainBlockedStatus() {
    // When
    boolean containsBlocked = false;
    for (AccountStatus status : AccountStatus.values()) {
      if (status == AccountStatus.BLOCKED) {
        containsBlocked = true;
        break;
      }
    }

    // Then
    assertTrue(containsBlocked);
  }

  @Test
  @DisplayName("Should contain CLOSED status")
  public void shouldContainClosedStatus() {
    // When
    boolean containsClosed = false;
    for (AccountStatus status : AccountStatus.values()) {
      if (status == AccountStatus.CLOSED) {
        containsClosed = true;
        break;
      }
    }

    // Then
    assertTrue(containsClosed);
  }

  @Test
  @DisplayName("Should convert string to enum value correctly")
  public void shouldConvertStringToEnumValueCorrectly() {
    // Then
    assertEquals(AccountStatus.ACTIVE, AccountStatus.valueOf("ACTIVE"));
    assertEquals(AccountStatus.BLOCKED, AccountStatus.valueOf("BLOCKED"));
    assertEquals(AccountStatus.CLOSED, AccountStatus.valueOf("CLOSED"));
  }

  @Test
  @DisplayName("Should throw exception for invalid enum value")
  public void shouldThrowExceptionForInvalidEnumValue() {
    // Then
    assertThrows(IllegalArgumentException.class, () -> {
      AccountStatus.valueOf("INVALID_STATUS");
    });
  }
}
