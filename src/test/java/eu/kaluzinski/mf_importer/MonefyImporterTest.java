package eu.kaluzinski.mf_importer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class MonefyImporterTest {

  private MonefyImporter testedUnit = new MonefyImporter();

  @Test
  void shouldImportBasicFile() {
    //given
    var dir = "src/test/resources/";
    var fileName = "basic_monefy_import.csv";
    var path = dir + fileName;

    //when
    var result = testedUnit.rawImportMonefyFile(path);

    //then
    var expectedResult = List.of(
        List.of("date", "account", "category", "amount", "currency", "converted amount", "currency",
            "description"),
        List.of("01.01.2024", "mBank", "Salary", "10000", "PLN", "10000", "PLN"),
        List.of("16.01.2024", "mBank", "Housing", "-2210", "PLN", "-2210", "PLN", "Rent"),
        List.of("01.02.2024", "mBank", "Internet", "-119", "PLN", "-119", "PLN", "UPC"),
        List.of("17.11.2024", "mBank", "Car", "-140", "PLN", "-140", "PLN", "Tires change")
    );

    assertEquals(expectedResult, result);
  }
}