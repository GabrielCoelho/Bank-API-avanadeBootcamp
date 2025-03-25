package br.com.devcoelho.apirest.bank.repository;

import br.com.devcoelho.apirest.bank.model.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
  List<Address> findByPersonId(Long personId);
}
