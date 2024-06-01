package eu.kaluzinski.mf_importer.reports;

import static java.util.stream.Collectors.groupingBy;

import eu.kaluzinski.mf_importer.emums.Metric;
import eu.kaluzinski.mf_importer.model.AccountEntry;
import eu.kaluzinski.mf_importer.model.AccountState;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BasicAccountReport implements AccountReport {

  @Override
  public Insights create(AccountState accountState) {
    var totalSpending = new Insight(Metric.TOTAL_ACCOUNT_SPEND, totalSpending(accountState));
    var spendByMonth = new Insight(Metric.TOTAL_ACCOUNT_SPEND_BY_MONTH, spendByMonth(accountState));

    return new Insights(List.of(totalSpending, spendByMonth));
  }

  private Double totalSpending(AccountState accountState) {
    return sumAccountEntries(accountState.expenses());
  }

  private Map<YearMonth, Double> spendByMonth(AccountState accountState) {
    return new TreeMap<>(groupAccountEntriesYearMonth(accountState.expenses())
        .entrySet()
        .parallelStream()
        .collect(Collectors.toMap(Entry::getKey,
            yearMonthAccountEntries -> sumAccountEntries(yearMonthAccountEntries.getValue()))));
  }

  private Map<YearMonth, List<AccountEntry>> groupAccountEntriesYearMonth(
      List<AccountEntry> accountEntries) {
    return accountEntries.stream()
        .collect(groupingBy(element -> YearMonth.from(element.date())));
  }

  //TODO average spend by month

  private Double sumAccountEntries(List<AccountEntry> entries) {
    return entries.stream()
        .mapToDouble(AccountEntry::amount)
        .sum();
  }

}
