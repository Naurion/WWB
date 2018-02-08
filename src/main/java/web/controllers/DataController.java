package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.Person;
import web.model.PersonRepository;

@RestController
public class DataController {

    @Autowired
    PersonRepository repository;

    @RequestMapping("/tablecreator")
    public String createTable(){
        repository.deleteAll();
        repository.save(new Person("Mike", "Tyson", "2nd Street", 60));
        return "Done";
    }

}
