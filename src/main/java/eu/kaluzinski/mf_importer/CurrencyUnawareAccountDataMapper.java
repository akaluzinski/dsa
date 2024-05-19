package eu.kaluzinski.mf_importer;

import eu.kaluzinski.mf_importer.emums.Header;
import eu.kaluzinski.mf_importer.model.AccountEntry;
import eu.kaluzinski.mf_importer.model.AccountState;
import java.util.List;

public class CurrencyUnawareAccountDataMapper implements AccountDataMapper {

  public AccountState mapImportDataToAccountState(List<List<String>> rawImportData) {
    var headers = mapRawImportRowToHeaders(rawImportData.get(0));

    var accountEntries = rawImportData.stream().map(
        rawEntry -> mapRawImportRowToAccountEntry(rawEntry)
    ).toList();

    var accountState = new AccountState(accountEntries, accountEntries);
    return accountState;
  }

  private AccountEntry mapRawImportRowToAccountEntry(List<String> rawImportRow) {
    return null;
  }

  private List<Header> mapRawImportRowToHeaders(List<String> rawImportRow) {
    return rawImportRow.stream()
        .map(String::toUpperCase)
        .map(cell -> cell.replaceAll(" ", "_"))
        .map(Header::valueOf).toList();
  }


}
