package br.com.devcoelho.apirest.bank.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTypeTest {

  @Test
  @DisplayName("Should have correct number of account types")
  public void shouldHaveCorrectNumberOfAccountTypes() {
    assertEquals(2, AccountType.values().length);
  }

  @Test
  @DisplayName("Should contain SIMPLE account type")
  public void shouldContainSimpleAccountType() {
    // When
    boolean containsSimple = false;
    for (AccountType type : AccountType.values()) {
      if (type == AccountType.SIMPLE) {
        containsSimple = true;
        break;
      }
    }

    // Then
    assertTrue(containsSimple);
  }

  @Test
  @DisplayName("Should contain INVESTMENT account type")
  public void shouldContainInvestmentAccountType() {
    // When
    boolean containsInvestment = false;
    for (AccountType type : AccountType.values()) {
      if (type == AccountType.INVESTMENT) {
        containsInvestment = true;
        break;
      }
    }

    // Then
    assertTrue(containsInvestment);
  }

  @Test
  @DisplayName("Should convert string to enum value correctly")
  public void shouldConvertStringToEnumValueCorrectly() {
    // Then
    assertEquals(AccountType.SIMPLE, AccountType.valueOf("SIMPLE"));
    assertEquals(AccountType.INVESTMENT, AccountType.valueOf("INVESTMENT"));
  }

  @Test
  @DisplayName("Should throw exception for invalid enum value")
  public void shouldThrowExceptionForInvalidEnumValue() {
    // Then
    assertThrows(IllegalArgumentException.class, () -> {
      AccountType.valueOf("INVALID_TYPE");
    });
  }
}
