package hello;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(PatientRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Patient("Jack", "Bauer"));
            repository.save(new Patient("Chloe", "O'Brian"));
            repository.save(new Patient("Kim", "Bauer"));
            repository.save(new Patient("David", "Palmer"));
            repository.save(new Patient("Michelle", "Dessler"));

            // fetch all Patient
            log.info("Patient found with findAll():");
            log.info("-------------------------------");
            for (Patient patient : repository.findAll()) {
                log.info(patient.toString());
            }
            log.info("");

            // fetch an individual Patient by ID
            repository.findById(1L)
                    .ifPresent(patient -> {
                        log.info("Patient found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(patient.toString());
                        log.info("");
                    });

            // fetch patient by last name
            log.info("patient found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info("");
        };
    }

}
