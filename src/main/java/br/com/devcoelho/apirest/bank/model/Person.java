package br.com.devcoelho.apirest.bank.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Table(name = "persons")
@Entity
@Data
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Address> address = new ArrayList<>();

  @Column(unique = true)
  private String cpf;
}
