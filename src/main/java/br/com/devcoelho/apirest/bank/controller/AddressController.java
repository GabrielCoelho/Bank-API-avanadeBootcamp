package br.com.devcoelho.apirest.bank.controller;

import br.com.devcoelho.apirest.bank.model.Address;
import br.com.devcoelho.apirest.bank.service.AddressService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

  private final AddressService addressService;

  @Autowired
  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @GetMapping
  public ResponseEntity<List<Address>> getAllAddresses() {
    List<Address> addresses = addressService.getAllAddresses();
    return ResponseEntity.ok(addresses);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
    return addressService
        .getAddressById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/person/{personId}/address")
  public ResponseEntity<List<Address>> getAddressesByPersonId(@PathVariable Long personId) {
    List<Address> addresses = addressService.getAddressesByPersonId(personId);
    if (addresses.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(addresses);
  }

  @PostMapping("/person/{personId}/address")
  public ResponseEntity<Address> addAddressToPerson(
      @PathVariable long personId, @Valid @RequestBody Address address) {

    return addressService
        .addAddressToPerson(personId, address)
        .map(savedAddress -> ResponseEntity.status(HttpStatus.CREATED).body(savedAddress))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/{id}")
  public ResponseEntity<Address> updateAddress(
      @PathVariable Long id, @Valid @RequestBody Address address) {
    return addressService
        .updateAddress(id, address)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/person/{id}")
  public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
    boolean deleted = addressService.deleteAddress(id);
    if (deleted) {
      return ResponseEntity.ok("Address removed");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
