package br.com.devcoelho.apirest.bank.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class RelationshipTest {

  @Autowired private TestEntityManager entityManager;

  @Test
  public void testPersonWithMultipleAccounts() {
    // Criar uma pessoa
    Person person = new Person();
    person.setName("Alice Johnson");
    person.setCpf("12378945600");
    entityManager.persist(person);

    // Criar múltiplas contas para a mesma pessoa
    Account account1 = new Account();
    account1.setAccountNumber("AC111111");
    account1.setAgency("001");
    account1.setBalance(new BigDecimal("2000.00"));
    account1.setClient(person);
    account1.setOpeningDate(LocalDateTime.now());
    entityManager.persist(account1);

    Account account2 = new Account();
    account2.setAccountNumber("AC222222");
    account2.setAgency("001");
    account2.setBalance(new BigDecimal("5000.00"));
    account2.setClient(person);
    account2.setOpeningDate(LocalDateTime.now());
    entityManager.persist(account2);

    entityManager.flush();

    // Buscar todas as contas dessa pessoa usando JPQL
    List<Account> accounts =
        entityManager
            .getEntityManager()
            .createQuery("SELECT a FROM Account a WHERE a.client.id = :personId", Account.class)
            .setParameter("personId", person.getId())
            .getResultList();

    // Verificar se ambas as contas foram encontradas
    assertEquals(2, accounts.size());
    assertTrue(accounts.stream().anyMatch(a -> a.getAccountNumber().equals("AC111111")));
    assertTrue(accounts.stream().anyMatch(a -> a.getAccountNumber().equals("AC222222")));
  }
}
