package br.com.devcoelho.apirest.bank.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class BrazilianStateTest {

  @Test
  @DisplayName("Should return correct full name for each state")
  public void getFullNameShouldReturnCorrectValue() {
    // Then
    assertEquals("São Paulo", BrazilianState.SP.getFullName());
    assertEquals("Rio de Janeiro", BrazilianState.RJ.getFullName());
    assertEquals("Minas Gerais", BrazilianState.MG.getFullName());
    assertEquals("Bahia", BrazilianState.BA.getFullName());
  }

  @Test
  @DisplayName("Should return correct abbreviation for each state")
  public void getAbbreviationShouldReturnCorrectValue() {
    // Then
    assertEquals("SP", BrazilianState.SP.getAbbreviation());
    assertEquals("RJ", BrazilianState.RJ.getAbbreviation());
    assertEquals("MG", BrazilianState.MG.getAbbreviation());
    assertEquals("BA", BrazilianState.BA.getAbbreviation());
  }

  @ParameterizedTest
  @CsvSource({
      "SP, São Paulo",
      "RJ, Rio de Janeiro",
      "MG, Minas Gerais",
      "BA, Bahia"
  })
  @DisplayName("Should confirm correct mapping between abbreviation and full name")
  public void mappingBetweenAbbreviationAndFullNameShouldBeCorrect(String abbreviation, String fullName) {
    // Given
    BrazilianState state = BrazilianState.valueOf(abbreviation);

    // Then
    assertEquals(fullName, state.getFullName());
    assertEquals(abbreviation, state.getAbbreviation());
  }

  @ParameterizedTest
  @ValueSource(strings = { "SP", "RJ", "MG", "sp", "rj", "mg" })
  @DisplayName("Should find state from valid abbreviation regardless of case")
  public void fromAbbreviationShouldFindStateFromValidAbbreviation(String abbreviation) {
    // When
    BrazilianState state = BrazilianState.fromAbbreviation(abbreviation);

    // Then
    assertNotNull(state);
    assertEquals(abbreviation.toUpperCase(), state.getAbbreviation());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = { "XX", "ZZ", "00" })
  @DisplayName("Should return null for invalid or empty abbreviation")
  public void fromAbbreviationShouldReturnNullForInvalidAbbreviation(String abbreviation) {
    // When
    BrazilianState state = BrazilianState.fromAbbreviation(abbreviation);

    // Then
    assertNull(state);
  }

  @ParameterizedTest
  @ValueSource(strings = { "São Paulo", "Rio de Janeiro", "Minas Gerais", "são paulo", "rio de janeiro" })
  @DisplayName("Should find state from valid full name regardless of case")
  public void fromFullNameShouldFindStateFromValidFullName(String fullName) {
    // When
    BrazilianState state = BrazilianState.fromFullName(fullName);

    // Then
    assertNotNull(state);
    assertEquals(fullName.toLowerCase(), state.getFullName().toLowerCase());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = { "Invalid State", "Not a state", "São Paula" })
  @DisplayName("Should return null for invalid or empty full name")
  public void fromFullNameShouldReturnNullForInvalidFullName(String fullName) {
    // When
    BrazilianState state = BrazilianState.fromFullName(fullName);

    // Then
    assertNull(state);
  }

  @Test
  @DisplayName("Should have 27 states total")
  public void shouldHaveCorrectNumberOfStates() {
    // There are 26 states plus the Federal District in Brazil
    assertEquals(27, BrazilianState.values().length);
  }
}
