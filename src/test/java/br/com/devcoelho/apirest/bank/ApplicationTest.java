package br.com.devcoelho.apirest.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

  @Test
  @DisplayName("Context loads successfully")
  public void contextLoads() {
    // This test will fail if the Spring context cannot be loaded
    // It verifies that our Spring Boot application can start properly
    assertTrue(true);
  }

  @Test
  @DisplayName("Application class should have main method")
  public void applicationShouldHaveMainMethod() throws NoSuchMethodException {
    // Check that Application class has a public static void main method
    assertNotNull(Application.class.getMethod("main", String[].class));
  }
}
