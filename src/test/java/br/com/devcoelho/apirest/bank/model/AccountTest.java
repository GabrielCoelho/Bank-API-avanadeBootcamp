package br.com.devcoelho.apirest.bank.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class AccountTest {

  @Autowired private TestEntityManager entityManager;

  private Account account;
  private Person person;

  @BeforeEach
  public void setup() {
    // Criar uma pessoa para associar à conta
    person = new Person();
    person.setName("John Doe");
    person.setCpf("12345678900");
    entityManager.persist(person);

    // Criar conta para testes
    account = new Account();
    account.setAccountNumber("AC123456");
    account.setAgency("001");
    account.setBalance(new BigDecimal("1000.00"));
    account.setClient(person);
    account.setOpeningDate(LocalDateTime.now());
    entityManager.persist(account);
    entityManager.flush();
  }

  @Test
  public void testAccountCreation() {
    // Buscar a conta do banco de dados
    Account savedAccount = entityManager.find(Account.class, account.getId());

    // Verificar se foi salva corretamente
    assertNotNull(savedAccount);
    assertEquals("AC123456", savedAccount.getAccountNumber());
    assertEquals("001", savedAccount.getAgency());
    assertEquals(0, new BigDecimal("1000.00").compareTo(savedAccount.getBalance()));
    assertEquals(person.getId(), savedAccount.getClient().getId());
  }

  @Test
  public void testDeposit() {
    // Depositar valor na conta
    account.deposit(new BigDecimal("500.00"));
    entityManager.persist(account);
    entityManager.flush();

    // Verificar se o saldo foi atualizado
    Account updatedAccount = entityManager.find(Account.class, account.getId());
    assertEquals(0, new BigDecimal("1500.00").compareTo(updatedAccount.getBalance()));
  }

  @Test
  public void testWithdraw() {
    // Sacar valor da conta
    account.withdraw(new BigDecimal("300.00"));
    entityManager.persist(account);
    entityManager.flush();

    // Verificar se o saldo foi atualizado
    Account updatedAccount = entityManager.find(Account.class, account.getId());
    assertEquals(0, new BigDecimal("700.00").compareTo(updatedAccount.getBalance()));
  }

  // Este teste irá falhar até que você implemente as exceções
  // Você pode comentá-lo por enquanto
  /*
  @Test
  public void testWithdrawInsufficientFunds() {
      // Tentar sacar mais do que tem na conta
      assertThrows(IllegalArgumentException.class, () -> {
          account.withdraw(new BigDecimal("2000.00"));
      });
  }
  */
}
