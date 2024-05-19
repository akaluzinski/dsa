package eu.kaluzinski.mf_importer;

import static eu.kaluzinski.mf_importer.MonefyTestUtils.IMPORT_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.kaluzinski.mf_importer.model.AccountEntry;
import eu.kaluzinski.mf_importer.model.AccountState;
import eu.kaluzinski.mf_importer.model.Category;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class CurrencyUnawareAccountDataMapperTest {

  private static final CurrencyUnawareAccountDataMapper testedUnit = new CurrencyUnawareAccountDataMapper(
      new CurrencyUnawareHeaderMapper());

  @Test
  void shouldMapImportDataToAccountState() {
    //given
    var rawData = List.of(
        IMPORT_HEADER,
        List.of("01.01.2024", "mBank", "Salary", "10000", "PLN", "10000", "PLN"),
        List.of("16.01.2024", "mBank", "Housing", "-2210", "PLN", "-2210", "PLN", "Rent"),
        List.of("01.02.2024", "mBank", "Internet", "-119", "PLN", "-119", "PLN", "UPC"),
        List.of("17.11.2024", "mBank", "Car", "-140.5", "PLN", "-140", "PLN", "Tires change")
    );

    //when
    var result = testedUnit.mapImportDataToAccountState(rawData);

    //then
    var expectedIncomes = List.of(
        new AccountEntry(LocalDate.of(2024, 1, 1), new Category("Salary"), 10000.0, "")
    );
    var expectedOutcomes = List.of(
        new AccountEntry(LocalDate.of(2024, 1, 16), new Category("Housing"), 2210.0, "Housing"),
        new AccountEntry(LocalDate.of(2024, 2, 1), new Category("Internet"), 119.0, "Rent"),
        new AccountEntry(LocalDate.of(2024, 11, 17), new Category("Car"), 140.5, "Tires change")
    );

    var expectedAccountState = new AccountState(expectedIncomes, expectedOutcomes);
    assertEquals(expectedAccountState, result);
  }

}