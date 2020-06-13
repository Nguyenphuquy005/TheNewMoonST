package stackjava.com.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import stackjava.com.mongodb.entities.Customer;
import stackjava.com.mongodb.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class WebServiceControler {
    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> findAllProduct() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @RequestMapping(value = "/customer/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getProductById(
            @PathVariable("id") String id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (!customer.isPresent()) {
            return new ResponseEntity<>(customer.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }
    @RequestMapping(value = "/customer",
            method = RequestMethod.POST)
    public ResponseEntity<Customer> createProduct(
            @RequestBody Customer product,
            UriComponentsBuilder builder) {
        customerRepository.save(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/customer/{id}")
                .buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/customer/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateProduct(
            @PathVariable("id") String id,
            @RequestBody Customer customer) {
        Optional<Customer> currentProduct = customerRepository
                .findById(id);

        if (!currentProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentProduct.get().setName(customer.getName());
        currentProduct.get().setAddress(customer.getAddress());
        customerRepository.save(currentProduct.get());
        return new ResponseEntity<>(currentProduct.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Customer> deleteProduct(
            @PathVariable("id") String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerRepository.delete(customer.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
