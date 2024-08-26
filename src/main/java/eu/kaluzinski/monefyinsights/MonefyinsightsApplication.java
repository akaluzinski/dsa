package eu.kaluzinski.monefyinsights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "eu.kaluzinski.controller")
public class MonefyinsightsApplication {

  public static void main(String[] args) {
    SpringApplication.run(MonefyinsightsApplication.class, args);
  }

}
