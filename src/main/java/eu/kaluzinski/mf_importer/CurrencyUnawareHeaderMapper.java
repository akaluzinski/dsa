package eu.kaluzinski.mf_importer;

import eu.kaluzinski.mf_importer.emums.Header;
import java.util.ArrayList;
import java.util.List;

public class CurrencyUnawareHeaderMapper implements HeaderMapper {

  @Override
  public List<Header> mapHeaders(List<String> headers) {
    return normalizeHeaders(headers)
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
