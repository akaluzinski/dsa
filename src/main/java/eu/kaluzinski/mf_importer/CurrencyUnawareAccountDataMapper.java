package eu.kaluzinski.mf_importer;

import eu.kaluzinski.mf_importer.emums.Header;
import eu.kaluzinski.mf_importer.model.AccountEntry;
import eu.kaluzinski.mf_importer.model.AccountState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CurrencyUnawareAccountDataMapper implements AccountDataMapper {

  public AccountState mapImportDataToAccountState(List<List<String>> rawImportData) {
    var headers = mapRawImportRowToHeaders(rawImportData.get(0));

    var accountEntries = rawImportData.stream().map(
        rawEntry -> mapRawImportRowToAccountEntry(rawEntry, headers)
    ).toList();

    var accountState = new AccountState(accountEntries, accountEntries);
    return accountState;
  }

  private AccountEntry mapRawImportRowToAccountEntry(List<String> rawImportRow,
      List<Header> headers) {

    Map<String, Integer> map = IntStream.range(0, headers.size())
        .boxed()
        .collect(Collectors.toMap(
            rawImportRow::get,
            Function.identity()
        ));

    return null;
  }

  private List<Header> mapRawImportRowToHeaders(List<String> rawImportRow) {
    return normalizeHeaders(rawImportRow)
        .stream()
        .map(String::toUpperCase)
        .map(cell -> cell.replaceAll(" ", "_"))
        .map(Header::valueOf)
        .toList();
  }

  private List<String> normalizeHeaders(List<String> rawImportRow) {
    var firstCurrencyIndex = rawImportRow.indexOf(Header.CURRENCY.getName());
    var lastCurrencyIndex = rawImportRow.lastIndexOf(Header.CURRENCY.getName());
    if (firstCurrencyIndex == lastCurrencyIndex) {
      return rawImportRow;
    }
    var normalizedHeadersRow = new ArrayList<>(rawImportRow);
    normalizedHeadersRow.set(lastCurrencyIndex, Header.CONVERTED_CURRENCY.getName());
    return normalizedHeadersRow;
  }
}
