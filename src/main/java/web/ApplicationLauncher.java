package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import web.model.Person;
import web.model.PersonRepository;

@SpringBootApplication
public class ApplicationLauncher implements CommandLineRunner{

    @Autowired
    PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);
    }

    public void run(String... strings) throws Exception {
        repository.save(new Person("John", "Smith", "1st Street", 21));
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repository.save(new Person("Mike", "Tyson", "2nd Street", 60));

    }
}
