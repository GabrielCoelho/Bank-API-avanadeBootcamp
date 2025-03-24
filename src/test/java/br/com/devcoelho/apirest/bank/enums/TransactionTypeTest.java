package br.com.devcoelho.apirest.bank.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TransactionTypeTest {

  @Test
  @DisplayName("Should have correct number of transaction types")
  public void shouldHaveCorrectNumberOfTransactionTypes() {
    assertEquals(5, TransactionType.values().length);
  }

  @ParameterizedTest
  @CsvSource({
      "DEPOSIT, Deposit",
      "WITHDRAWAL, Withdrawal",
      "TRANSFER, Transfer",
      "INTEREST, Interest Earned",
      "FEE, Account Fee"
  })
  @DisplayName("Should return correct description for each transaction type")
  public void getDescriptionShouldReturnCorrectValue(TransactionType type, String expectedDescription) {
    // Then
    assertEquals(expectedDescription, type.getDescription());
  }

  @Test
  @DisplayName("Each transaction type should have unique description")
  public void eachTransactionTypeShouldHaveUniqueDescription() {
    // Create array to store all descriptions
    String[] descriptions = new String[TransactionType.values().length];

    // Get all descriptions
    for (int i = 0; i < TransactionType.values().length; i++) {
      descriptions[i] = TransactionType.values()[i].getDescription();
    }

    // Check for duplicates by comparing each description with all others
    for (int i = 0; i < descriptions.length; i++) {
      for (int j = i + 1; j < descriptions.length; j++) {
        assertNotEquals(descriptions[i], descriptions[j],
            "Transaction types should have unique descriptions");
      }
    }
  }

  @Test
  @DisplayName("Should confirm DEPOSIT has correct description")
  public void depositShouldHaveCorrectDescription() {
    assertEquals("Deposit", TransactionType.DEPOSIT.getDescription());
  }

  @Test
  @DisplayName("Should confirm WITHDRAWAL has correct description")
  public void withdrawalShouldHaveCorrectDescription() {
    assertEquals("Withdrawal", TransactionType.WITHDRAWAL.getDescription());
  }

  @Test
  @DisplayName("Should confirm TRANSFER has correct description")
  public void transferShouldHaveCorrectDescription() {
    assertEquals("Transfer", TransactionType.TRANSFER.getDescription());
  }

  @Test
  @DisplayName("Should confirm INTEREST has correct description")
  public void interestShouldHaveCorrectDescription() {
    assertEquals("Interest Earned", TransactionType.INTEREST.getDescription());
  }

  @Test
  @DisplayName("Should confirm FEE has correct description")
  public void feeShouldHaveCorrectDescription() {
    assertEquals("Account Fee", TransactionType.FEE.getDescription());
  }
}
