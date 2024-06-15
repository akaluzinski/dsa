package eu.kaluzinski.monefyinsights;

import static eu.kaluzinski.mf_importer.flow.FlowsProvider.basicFlow;

import eu.kaluzinski.mf_importer.converters.JsonConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MonefyinsightsApplication {

  public static void main(String[] args) {
    SpringApplication.run(MonefyinsightsApplication.class, args);
  }

  @GetMapping("/insights")
  public String sayHello() {
    var insights = basicFlow().importAndGenerate("src/main/resources/import.csv", "mBank");
    var stringifiedInsights = new JsonConverter().toJson(insights);

    return stringifiedInsights;
  }

}
