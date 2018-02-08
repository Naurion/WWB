package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import web.model.PersonRepository;

@SpringBootApplication
public class ApplicationLauncher implements CommandLineRunner{

    @Autowired
    PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);
    }

    public void run(String... strings) throws Exception {
        repository.deleteAll();
    }
}
