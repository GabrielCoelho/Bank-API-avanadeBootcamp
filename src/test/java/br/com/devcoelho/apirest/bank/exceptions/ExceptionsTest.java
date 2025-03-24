package br.com.devcoelho.apirest.bank.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários para as classes de exceção personalizadas do sistema bancário. Estas exceções
 * são utilizadas para sinalizar diferentes tipos de erros durante as operações bancárias.
 */
public class ExceptionsTest {

  @Test
  @DisplayName("InsufficientBalanceException deve armazenar e retornar a mensagem corretamente")
  public void insufficientBalanceExceptionShouldStoreAndReturnMessageCorrectly() {
    // Arrange - Preparação do cenário de teste
    String errorMessage = "Saldo insuficiente para completar a operação";

    // Act - Execução da operação sendo testada
    InsufficientBalanceException exception = new InsufficientBalanceException(errorMessage);

    // Assert - Verificação do resultado obtido
    assertEquals(
        errorMessage,
        exception.getMessage(),
        "A mensagem de erro deve ser armazenada e retornável pelo método getMessage()");
  }

  @Test
  @DisplayName("InvalidAccountException deve armazenar e retornar a mensagem corretamente")
  public void invalidAccountExceptionShouldStoreAndReturnMessageCorrectly() {
    // Arrange - Preparação do cenário de teste
    String errorMessage = "Conta inválida ou não encontrada";

    // Act - Execução da operação sendo testada
    InvalidAccountException exception = new InvalidAccountException(errorMessage);

    // Assert - Verificação do resultado obtido
    assertEquals(
        errorMessage,
        exception.getMessage(),
        "A mensagem de erro deve ser armazenada e retornável pelo método getMessage()");
  }

  @Test
  @DisplayName("InvalidOperationException deve armazenar e retornar a mensagem corretamente")
  public void invalidOperationExceptionShouldStoreAndReturnMessageCorrectly() {
    // Arrange - Preparação do cenário de teste
    String errorMessage = "Operação não permitida para este tipo de conta";

    // Act - Execução da operação sendo testada
    InvalidOperationException exception = new InvalidOperationException(errorMessage);

    // Assert - Verificação do resultado obtido
    assertEquals(
        errorMessage,
        exception.getMessage(),
        "A mensagem de erro deve ser armazenada e retornável pelo método getMessage()");
  }

  @Test
  @DisplayName("Todas as exceções personalizadas devem estender RuntimeException")
  public void allCustomExceptionsShouldExtendRuntimeException() {
    // Arrange - Criação das instâncias das exceções
    InsufficientBalanceException insufficientBalanceException =
        new InsufficientBalanceException("Teste");
    InvalidAccountException invalidAccountException = new InvalidAccountException("Teste");
    InvalidOperationException invalidOperationException = new InvalidOperationException("Teste");

    // Assert - Verificação da hierarquia de classes
    assertTrue(
        insufficientBalanceException instanceof RuntimeException,
        "InsufficientBalanceException deve ser uma subclasse de RuntimeException");
    assertTrue(
        invalidAccountException instanceof RuntimeException,
        "InvalidAccountException deve ser uma subclasse de RuntimeException");
    assertTrue(
        invalidOperationException instanceof RuntimeException,
        "InvalidOperationException deve ser uma subclasse de RuntimeException");
  }

  @Test
  @DisplayName("Exceções personalizadas devem ser capturáveis como RuntimeException")
  public void customExceptionsShouldBeCatchableAsRuntimeException() {
    // Assert - Verificação do comportamento em blocos try-catch
    assertDoesNotThrow(
        () -> {
          try {
            // Lançando InsufficientBalanceException
            throw new InsufficientBalanceException("Saldo insuficiente");
          } catch (RuntimeException e) {
            // Deve ser capturada aqui como RuntimeException
            assertTrue(
                e instanceof InsufficientBalanceException,
                "A exceção capturada deve ser do tipo InsufficientBalanceException");
          }

          try {
            // Lançando InvalidAccountException
            throw new InvalidAccountException("Conta inválida");
          } catch (RuntimeException e) {
            // Deve ser capturada aqui como RuntimeException
            assertTrue(
                e instanceof InvalidAccountException,
                "A exceção capturada deve ser do tipo InvalidAccountException");
          }

          try {
            // Lançando InvalidOperationException
            throw new InvalidOperationException("Operação inválida");
          } catch (RuntimeException e) {
            // Deve ser capturada aqui como RuntimeException
            assertTrue(
                e instanceof InvalidOperationException,
                "A exceção capturada deve ser do tipo InvalidOperationException");
          }
        },
        "Todas as exceções personalizadas devem ser capturáveis como RuntimeException");
  }

  @Test
  @DisplayName("Exceções personalizadas devem preservar a causa original")
  public void customExceptionsShouldPreserveCause() {
    // Arrange - Criação de uma exceção que será a causa
    IllegalArgumentException originalCause = new IllegalArgumentException("Valor inválido");

    // Act - Criação das exceções personalizadas com causa
    InsufficientBalanceException insufficientBalanceException =
        new InsufficientBalanceException("Erro de saldo");
    insufficientBalanceException.initCause(originalCause);

    InvalidAccountException invalidAccountException = new InvalidAccountException("Erro de conta");
    invalidAccountException.initCause(originalCause);

    InvalidOperationException invalidOperationException =
        new InvalidOperationException("Erro de operação");
    invalidOperationException.initCause(originalCause);

    // Assert - Verificação da causa
    assertEquals(
        originalCause,
        insufficientBalanceException.getCause(),
        "A causa da exceção InsufficientBalanceException deve ser preservada");
    assertEquals(
        originalCause,
        invalidAccountException.getCause(),
        "A causa da exceção InvalidAccountException deve ser preservada");
    assertEquals(
        originalCause,
        invalidOperationException.getCause(),
        "A causa da exceção InvalidOperationException deve ser preservada");
  }
}
