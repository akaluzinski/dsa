package eu.kaluzinski.mf_importer;

import static eu.kaluzinski.mf_importer.MonefyTestUtils.IMPORT_HEADER;
import static eu.kaluzinski.mf_importer.emums.Header.ACCOUNT;
import static eu.kaluzinski.mf_importer.emums.Header.AMOUNT;
import static eu.kaluzinski.mf_importer.emums.Header.CATEGORY;
import static eu.kaluzinski.mf_importer.emums.Header.CONVERTED_AMOUNT;
import static eu.kaluzinski.mf_importer.emums.Header.CONVERTED_CURRENCY;
import static eu.kaluzinski.mf_importer.emums.Header.CURRENCY;
import static eu.kaluzinski.mf_importer.emums.Header.DATE;
import static eu.kaluzinski.mf_importer.emums.Header.DESCRIPTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

class CurrencyUnawareHeaderMapperTest {

  private static final HeaderMapper testedUnit = new CurrencyUnawareHeaderMapper();

  @Test
  void shouldMapHeaders() {
    var expectedHeaders = List.of(DATE, ACCOUNT, CATEGORY, AMOUNT, CURRENCY, CONVERTED_AMOUNT,
        CONVERTED_CURRENCY, DESCRIPTION);

    var result = testedUnit.mapHeaders(IMPORT_HEADER);

    assertEquals(expectedHeaders, result);
  }

  @Test
  void shouldGetHeaderIndices() {
    var headers = List.of(DATE, ACCOUNT, CATEGORY);

    var expectedIndexes = new LinkedHashMap<>();
    expectedIndexes.put(DATE, 0);
    expectedIndexes.put(ACCOUNT, 1);
    expectedIndexes.put(CATEGORY, 2);

    var result = testedUnit.getHeaderIndexes(headers);

    assertEquals(expectedIndexes, result);
  }
}