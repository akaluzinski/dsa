package eu.kaluzinski.mf_importer;

import eu.kaluzinski.mf_importer.emums.Header;
import eu.kaluzinski.mf_importer.model.AccountEntry;
import eu.kaluzinski.mf_importer.model.AccountState;
import eu.kaluzinski.mf_importer.model.Category;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class CurrencyUnawareAccountDataMapper implements AccountDataMapper {

  private static final String DATE_FORMAT = "dd.MM.yyyy";
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
  private final HeaderMapper headerMapper;

  public CurrencyUnawareAccountDataMapper(HeaderMapper headerMapper) {
    this.headerMapper = headerMapper;
  }

  public AccountState mapImportDataToAccountState(List<List<String>> rawImportData) {
    var headers = headerMapper.mapHeaders(rawImportData.get(0));
    var headersIndexes = headerMapper.getHeaderIndexes(headers);

    var accountEntries = rawImportData.subList(1, rawImportData.size())
        .stream()
        .map(rawEntry -> mapRawImportRowToAccountEntry(rawEntry, headersIndexes))
        .toList();

    var accountState = new AccountState(accountEntries, accountEntries);
    return accountState;
  }

  private AccountEntry mapRawImportRowToAccountEntry(List<String> rawImportRow,
      Map<Header, Integer> headersIndexes) {

    // todo fix duplicates
    var rawDate = rawImportRow.get(headersIndexes.get(Header.DATE));
    var rawCategory = rawImportRow.get(headersIndexes.get(Header.CATEGORY));
    var rawAmount = rawImportRow.get(headersIndexes.get(Header.AMOUNT));
    var rawDescription = rawImportRow.get(headersIndexes.get(Header.DESCRIPTION));

    var parsedDate = LocalDate.parse(rawDate, DATE_FORMATTER);
    var category = new Category(rawCategory);
    var amount = Double.parseDouble(rawAmount);

    return new AccountEntry(parsedDate, category, amount, rawDescription);
  }


}
