package br.com.devcoelho.apirest.bank.model;

import static org.junit.jupiter.api.Assertions.*;

import br.com.devcoelho.apirest.bank.enums.BrazilianState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class PersonTest {

  @Autowired private TestEntityManager entityManager;

  @Test
  public void testPersonCreation() {
    // Criar uma pessoa
    Person person = new Person();
    person.setName("Jane Doe");
    person.setCpf("98765432100");

    // Persistir pessoa
    entityManager.persist(person);
    entityManager.flush();

    // Verificar se foi salva corretamente
    Person savedPerson = entityManager.find(Person.class, person.getId());
    assertNotNull(savedPerson);
    assertEquals("Jane Doe", savedPerson.getName());
    assertEquals("98765432100", savedPerson.getCpf());
  }

  @Test
  public void testPersonWithAddress() {
    // Criar uma pessoa
    Person person = new Person();
    person.setName("Bob Smith");
    person.setCpf("45678912300");

    // Criar e adicionar endereço
    Address address = new Address();
    address.setAddress("Rua Principal");
    address.setHouseNumber("123");
    address.setNeighborhood("Centro");
    address.setCityName("São Paulo");
    address.setState(BrazilianState.SP);
    address.setCepNumber("01234-567");
    address.setPerson(person);

    person.getAddress().add(address);

    // Persistir pessoa (o endereço deve ser persistido em cascata)
    entityManager.persist(person);
    entityManager.flush();

    // Verificar se pessoa e endereço foram salvos corretamente
    Person savedPerson = entityManager.find(Person.class, person.getId());
    assertNotNull(savedPerson);
    assertEquals(1, savedPerson.getAddress().size());

    Address savedAddress = savedPerson.getAddress().get(0);
    assertEquals("Rua Principal", savedAddress.getAddress());
    assertEquals("São Paulo", savedAddress.getCityName());
    assertEquals(BrazilianState.SP, savedAddress.getState());
  }
}
