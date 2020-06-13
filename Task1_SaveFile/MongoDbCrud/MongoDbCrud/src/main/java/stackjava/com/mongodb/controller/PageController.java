package stackjava.com.mongodb.controller;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stackjava.com.mongodb.repository.CustomerRepository;

import java.util.logging.LogManager;

@RestController
@CrossOrigin(origins = {"*"})
public class PageController {

//    MongoClient

//    @Autowired
//    CustomerRepository customerRepository ;
//
//
//    @GetMapping(value = "page")
//    public String setPage(Model model){
//
//        model.addAttribute("listCustomer",customerRepository.findAll());
//        return "page/page-list-customer";
//    }
}
