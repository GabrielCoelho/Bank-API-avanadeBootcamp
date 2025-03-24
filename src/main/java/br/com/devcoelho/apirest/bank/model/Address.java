package br.com.devcoelho.apirest.bank.model;

import br.com.devcoelho.apirest.bank.enums.BrazilianState;
import lombok.Data;

@Data
public class Address {

  private String address;
  private String houseNumber;
  private String houseComplement;
  private String neighborhood;
  private String cityName;
  private BrazilianState state;
  private String cepNumber;
}
