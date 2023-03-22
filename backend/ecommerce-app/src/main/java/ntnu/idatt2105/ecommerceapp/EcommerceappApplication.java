package ntnu.idatt2105.ecommerceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class EcommerceappApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceappApplication.class, args);
    }
}
