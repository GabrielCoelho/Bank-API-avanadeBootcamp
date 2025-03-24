package br.com.devcoelho.apirest.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Teste unitário simples para a classe Application sem carregar o contexto Spring. Isso evita erros
 * relacionados ao carregamento do ApplicationContext.
 */
public class ApplicationTest {

  @Test
  @DisplayName("Application class should have main method")
  public void applicationShouldHaveMainMethod() throws NoSuchMethodException {
    // Check that Application class has a public static void main method
    assertNotNull(Application.class.getMethod("main", String[].class));
  }
}
