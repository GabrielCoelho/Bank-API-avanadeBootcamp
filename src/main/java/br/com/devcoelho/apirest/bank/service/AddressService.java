package br.com.devcoelho.apirest.bank.service;

import br.com.devcoelho.apirest.bank.model.Address;
import br.com.devcoelho.apirest.bank.model.Person;
import br.com.devcoelho.apirest.bank.repository.AddressRepository;
import br.com.devcoelho.apirest.bank.repository.PersonRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
  private final AddressRepository addressRepository;
  private final PersonRepository personRepository;

  @Autowired
  public AddressService(AddressRepository addressRepository, PersonRepository personRepository) {
    this.addressRepository = addressRepository;
    this.personRepository = personRepository;
  }

  public List<Address> getAllAddresses() {
    return addressRepository.findAll();
  }

  public List<Address> getAddressesByPersonId(Long personId) {
    return addressRepository.findByPersonId(personId);
  }

  public Optional<Address> getAddressById(Long id) {
    return addressRepository.findById(id);
  }

  @Transactional
  public Optional<Address> addAddressToPerson(Long personId, Address address) {

    Optional<Person> personOptional = personRepository.findById(personId);

    if (!personOptional.isPresent()) {
      return Optional.empty();
    }
    Person person = personOptional.get();
    address.setPerson(person);
    Address savedAddress = addressRepository.save(address);
    person.getAddress().add(savedAddress);
    return Optional.of(savedAddress);
  }

  @Transactional
  public Optional<Address> updateAddress(Long id, Address address) {
    Optional<Address> addressOptional = addressRepository.findById(id);

    if (!addressOptional.isPresent()) {
      return Optional.empty();
    }

    Address existingAddress = addressOptional.get();

    existingAddress.setAddress(address.getAddress());
    existingAddress.setHouseNumber(address.getHouseNumber());
    existingAddress.setHouseComplement(address.getHouseComplement());
    existingAddress.setNeighborhood(address.getNeighborhood());
    existingAddress.setCityName(address.getCityName());
    existingAddress.setState(address.getState());
    existingAddress.setCepNumber(address.getCepNumber());

    Address savedAddress = addressRepository.save(existingAddress);
    return Optional.of(savedAddress);
  }

  @Transactional
  public boolean deleteAddress(Long id) {
    Optional<Address> addressOptional = addressRepository.findById(id);
    if (!addressOptional.isPresent()) {
      return false;
    }

    Address address = addressOptional.get();
    Person person = address.getPerson();
    if (person != null) {
      person.getAddress().remove(address);
    }

    addressRepository.delete(address);
    return true;
  }
}
