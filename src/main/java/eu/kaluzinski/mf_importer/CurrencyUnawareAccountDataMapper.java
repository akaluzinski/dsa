package eu.kaluzinski.mf_importer;

import eu.kaluzinski.mf_importer.emums.Header;
import eu.kaluzinski.mf_importer.model.AccountEntry;
import eu.kaluzinski.mf_importer.model.AccountState;
import eu.kaluzinski.mf_importer.model.Category;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CurrencyUnawareAccountDataMapper implements AccountDataMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final HeaderMapper headerMapper;

    public CurrencyUnawareAccountDataMapper(HeaderMapper headerMapper) {
        this.headerMapper = headerMapper;
    }

    public AccountState mapImportDataToAccountState(String accountName, List<List<String>> rawImportData) {
        var headers = headerMapper.mapHeaders(rawImportData.get(0));
        var idx = headerMapper.getHeaderIndexes(headers);

        var incomes = new LinkedList<AccountEntry>();
        var outcomes = new LinkedList<AccountEntry>();

        rawImportData.stream().skip(1)
            .map(row -> mapRow(row, idx))
            .filter(e -> e.accountName().equals(accountName))
            .forEach(e -> {
                if (e.amount() >= 0) incomes.add(e);
                else outcomes.add(e.toAbs());
            });

        return new AccountState(incomes, outcomes);
    }

    private AccountEntry mapRow(List<String> row, Map<Header, Integer> idx) {
        var date = LocalDate.parse(row.get(idx.get(Header.DATE)), DATE_FORMATTER);
        var category = new Category(row.get(idx.get(Header.CATEGORY)));
        var amount = Double.parseDouble(row.get(idx.get(Header.AMOUNT)));
        var desc = row.get(idx.get(Header.DESCRIPTION));
        var account = row.get(idx.get(Header.ACCOUNT));
        return new AccountEntry(date, category, amount, desc, account);
    }
}