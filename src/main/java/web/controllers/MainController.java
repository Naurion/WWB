package web.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.model.Person;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private static List<Person> persons = new ArrayList<Person>();

    static {
        persons.add(new Person("John", "Smith", "1st Street", 21));
        persons.add(new Person("Mike", "Tyson", "2nd Street", 60));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String mainPage(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
    public String personListPage(Model model) {
        model.addAttribute("persons", persons);
        return "personList";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.GET)
    public String personAddPage(Model model) {

        Person person = new Person();
        model.addAttribute("person", person);

        return "addPerson";
    }

    @RequestMapping(value = {"/addPerson"}, method = RequestMethod.POST)
    public String savePerson(Model model, @ModelAttribute("person") Person person) {
        long id = person.getId();
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String address = person.getAddress();
        int age = person.getAge();


        if (firstName != null && firstName.length() > 0 && lastName != null && lastName.length() > 0) {
            Person newPerson = new Person(firstName, lastName, address, age);
            persons.add(newPerson);
            return "redirect:/personList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }
}
