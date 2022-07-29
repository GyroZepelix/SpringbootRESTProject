package com.gjalic.springdgjalic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDGjalicApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringDGjalicApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringDGjalicApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
            repository.save(new Customer("Ivan", "Tomic"));
            repository.save(new Customer("Luka", "Plejic", false));
            repository.save(new Customer("Luka", "Vuksanovic", false));
            repository.save(new Customer("Mislav", "Sabolski"));
            repository.save(new Customer("Marko", "Kedacic"));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Tomic").forEach(bauer -> {
                log.info(bauer.toString());
            });

            log.info("");
        };
    }
}
