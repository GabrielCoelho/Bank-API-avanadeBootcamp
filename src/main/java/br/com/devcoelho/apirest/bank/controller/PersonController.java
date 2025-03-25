package br.com.devcoelho.apirest.bank.controller;

import br.com.devcoelho.apirest.bank.model.Person;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

  private List<Person> persons = new ArrayList<>();

  @GetMapping
  public List<Person> getPersons() {
    return persons;
  }

  @GetMapping("/{id}")
  public Person getPerson(@PathVariable Long id) {
    return persons.stream().filter(person -> person.getId().equals(id)).findFirst().orElse(null);
  }

  @PostMapping
  public Person addPerson(@RequestBody Person person) {
    persons.add(person);
    return person;
  }

  @PutMapping("/{id}")
  public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {
    for (int i = 0; i < persons.size(); i++) {
      if (persons.get(i).getId().equals(id)) {
        persons.set(i, person);
        return person;
      }
    }
    return null;
  }

  @DeleteMapping("/{id}")
  public String deletePerson(@PathVariable Long id) {
    persons.removeIf(person -> person.getId().equals(id));
    return "Successfully removed";
  }
}
