module br.com.devcoelho {
  // Existing requires statements
  requires static lombok;
  requires jakarta.persistence;
  requires spring.beans;
  requires spring.context;
  requires spring.boot;
  requires spring.boot.autoconfigure;

  // Export your application package to Spring
  exports br.com.devcoelho.apirest.bank to
      spring.beans,
      spring.context,
      spring.boot;

  // Your existing model exports
  exports br.com.devcoelho.apirest.bank.model;
  exports br.com.devcoelho.apirest.bank.enums;

  // Open your packages for Spring's reflection
  opens br.com.devcoelho.apirest.bank to
      spring.core,
      spring.beans,
      spring.context;
  opens br.com.devcoelho.apirest.bank.model to
      spring.core,
      spring.beans,
      org.hibernate.orm.core;
// Open other packages that need reflection
}
