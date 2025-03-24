package br.com.devcoelho.apirest.bank.model;

import br.com.devcoelho.apirest.bank.enums.BrazilianState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "address")
@Entity
@Data
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String address;
  private String houseNumber;
  private String houseComplement;
  private String neighborhood;
  private String cityName;

  @Enumerated(EnumType.STRING)
  private BrazilianState state;

  private String cepNumber;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;
}
