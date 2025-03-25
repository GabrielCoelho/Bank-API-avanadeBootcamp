package br.com.devcoelho.apirest.bank.service;

import br.com.devcoelho.apirest.bank.model.Person;
import br.com.devcoelho.apirest.bank.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
  private final PersonRepository personRepository;

  @Autowired
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }

  public Optional<Person> getPersonById(Long id) {
    return personRepository.findById(id);
  }

  public Person savePerson(Person person) {
    return personRepository.save(person);
  }

  public Optional<Person> updatePerson(Long id, Person personDetails) {
    return personRepository
        .findById(id)
        .map(
            existingPerson -> {
              existingPerson.setName(personDetails.getName());
              existingPerson.setCpf(personDetails.getCpf());
              if (personDetails.getAddress() != null) {
                existingPerson.getAddress().clear();
                personDetails
                    .getAddress()
                    .forEach(
                        address -> {
                          address.setPerson(existingPerson);
                          existingPerson.getAddress().add(address);
                        });
              }
              return personRepository.save(existingPerson);
            });
  }

  public boolean deletePerson(Long id) {
    return personRepository
        .findById(id)
        .map(
            person -> {
              personRepository.delete(person);
              return true;
            })
        .orElse(false);
  }
}
