package eu.kaluzinski.mf_importer;

import static eu.kaluzinski.mf_importer.MonefyTestUtils.IMPORT_HEADER;
import static eu.kaluzinski.mf_importer.MonefyTestUtils.accountIncomeState;
import static eu.kaluzinski.mf_importer.MonefyTestUtils.accountOutcomeState;
import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.kaluzinski.mf_importer.model.AccountState;
import java.util.List;
import org.junit.jupiter.api.Test;

class CurrencyUnawareAccountDataMapperTest {

  private static final CurrencyUnawareAccountDataMapper testedUnit = new CurrencyUnawareAccountDataMapper(
      new CurrencyUnawareHeaderMapper());

  @Test
  void shouldMapImportDataToAccountState() {
    //given
    var normalizedData = List.of(
        IMPORT_HEADER,
        List.of("01.01.2024", "mBank", "Salary", "10000", "PLN", "10000", "PLN", ""),
        List.of("16.01.2024", "mBank", "Housing", "-2210", "PLN", "-2210", "PLN", "Rent"),
        List.of("01.02.2024", "mBank", "Internet", "-119", "PLN", "-119", "PLN", "UPC"),
        List.of("17.02.2024", "mBank", "Car", "-140.5", "PLN", "-140", "PLN", "Tires change"),
        List.of("19.02.2024", "mBank", "Food", "-1200.1", "PLN", "-140", "PLN", "Monthly food")
    );

    //when
    var result = testedUnit.mapImportDataToAccountState(normalizedData);

    //then
    var expectedAccountState = new AccountState(accountIncomeState, accountOutcomeState);
    assertEquals(expectedAccountState, result);
  }

}