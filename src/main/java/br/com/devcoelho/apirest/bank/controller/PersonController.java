package br.com.devcoelho.apirest.bank.controller;

import br.com.devcoelho.apirest.bank.model.Person;
import br.com.devcoelho.apirest.bank.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  public ResponseEntity<List<Person>> getPersons() {
    List<Person> persons = personService.getAllPersons();
    return ResponseEntity.ok(persons);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> getPerson(@PathVariable Long id) {
    return personService
        .getPersonById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Person> addPerson(@RequestBody Person person) {
    Person savedPerson = personService.savePerson(person);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
    return personService
        .updatePerson(id, person)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePerson(@PathVariable Long id) {
    boolean wasDeleted = personService.deletePerson(id);
    if (wasDeleted) {
      return ResponseEntity.ok("Successfully removed");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
