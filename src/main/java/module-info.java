module br.com.devcoelho {
  // Jakarta Persistence API
  requires static jakarta.persistence;

  // Lombok
  requires static lombok;

  // Spring Boot
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.context;
  requires spring.data.jpa;
  requires spring.beans;
  requires spring.web;

  // Exponha seus pacotes necessários
  exports br.com.devcoelho.apirest.bank.model;
  exports br.com.devcoelho.apirest.bank.enums;
}
