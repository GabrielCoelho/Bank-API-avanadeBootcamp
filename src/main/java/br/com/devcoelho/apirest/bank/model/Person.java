package br.com.devcoelho.apirest.bank.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Person {
  private String name;
  private List<Address> address = new ArrayList<>();
  private String cpf;
}
