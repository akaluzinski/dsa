package eu.kaluzinski.mf_importer.reports;

import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_INCOME_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_SAVINGS_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_SPEND_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND_BY_MONTH;
import static java.util.stream.Collectors.groupingBy;

import eu.kaluzinski.mf_importer.model.AccountEntry;
import eu.kaluzinski.mf_importer.model.AccountState;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BasicAccountReport implements AccountReport {

  private static final Double AVERAGE_OF_NO_VALUES = 0.0;

  @Override
  public Insights create(AccountState accountState) {
    var expenses = accountState.expenses();
    var incomes = accountState.incomes();
    var averageSpendByMonth = averageEntriesValueByMonth(expenses);
    var averageIncomeByMonth = averageEntriesValueByMonth(incomes);
    var averageSavingByMonth = averageIncomeByMonth - averageSpendByMonth;

    var averageSpendByMonthInsight = Insight.of(AVERAGE_SPEND_BY_MONTH, averageSpendByMonth);
    var averageIncomeByMonthInsight = Insight.of(AVERAGE_INCOME_BY_MONTH, averageIncomeByMonth);
    var averageSavingsByMonthInsight = Insight.of(AVERAGE_SAVINGS_BY_MONTH, averageSavingByMonth);
    var totalSpending = Insight.of(TOTAL_ACCOUNT_SPEND, totalSpending(accountState));
    var spendByMonth = Insight.of(TOTAL_ACCOUNT_SPEND_BY_MONTH, spendByMonth(expenses));

    return new Insights(
        List.of(averageSpendByMonthInsight, averageIncomeByMonthInsight,
            averageSavingsByMonthInsight, totalSpending, spendByMonth));
  }

  private Double averageEntriesValueByMonth(List<AccountEntry> accountEntries) {
    return spendByMonth(accountEntries)
        .entrySet()
        .parallelStream()
        .mapToDouble(Entry::getValue)
        .average()
        .orElse(AVERAGE_OF_NO_VALUES);
  }

  private Double totalSpending(AccountState accountState) {
    return sumAccountEntries(accountState.expenses());
  }


  private Map<YearMonth, Double> spendByMonth(List<AccountEntry> expenses) {
    return new TreeMap<>(groupAccountEntriesYearMonth(expenses)
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

  private Double sumAccountEntries(List<AccountEntry> entries) {
    return entries.stream()
        .mapToDouble(AccountEntry::amount)
        .sum();
  }

  private Double averageAccountEntries(List<AccountEntry> entries) {
    return entries.stream()
        .mapToDouble(AccountEntry::amount)
        .average()
        .orElseThrow();
  }

}
