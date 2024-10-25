package eu.kaluzinski.mf_importer.reports;

import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_INCOME_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_SAVINGS_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_SPEND_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.BASIC_COSTS_OF_LIVING;
import static eu.kaluzinski.mf_importer.emums.Metric.COST_OF_LIVING;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_INCOME_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND_BY_MONTH_EXCLUDING_INVESTMENTS;
import static eu.kaluzinski.mf_importer.model.CategoryNames.investmentCategoryNames;
import static eu.kaluzinski.mf_importer.model.CategoryNames.whimCategoryNames;
import static java.util.stream.Collectors.groupingBy;

import eu.kaluzinski.mf_importer.model.AccountEntry;
import eu.kaluzinski.mf_importer.model.AccountState;
import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BasicAccountReport implements AccountReport {

  private static final Double AVERAGE_OF_NO_VALUES = 0.0;

  @Override
  public Insights create(AccountState accountState) {
    return create(accountState, false);
  }

  public Insights create(AccountState accountState, boolean includeCustom) {
    var expenses = accountState.expenses();
    var expensesExcludingInvestments = filterOutExpenseType(expenses, investmentCategoryNames);
    var expensesExcludingInvestmentsAndWhims = filterOutExpenseType(expensesExcludingInvestments,
        whimCategoryNames);
    var incomes = accountState.incomes();

    var averageSpendByMonth = averageEntriesValueByMonth(expenses);
    var averageCostOfLiving = averageEntriesValueByMonth(expensesExcludingInvestmentsAndWhims);
    var averageIncomeByMonth = averageEntriesValueByMonth(incomes);
    var averageSavingByMonth = averageIncomeByMonth - averageSpendByMonth;

    var averageSpendByMonthInsight = Insight.of(AVERAGE_SPEND_BY_MONTH, averageSpendByMonth);
    var averageIncomeByMonthInsight = Insight.of(AVERAGE_INCOME_BY_MONTH, averageIncomeByMonth);
    var averageSavingsByMonthInsight = Insight.of(AVERAGE_SAVINGS_BY_MONTH, averageSavingByMonth);
    var costOfLiving = Insight.of(COST_OF_LIVING, averageCostOfLiving);
    var totalSpending = Insight.of(TOTAL_ACCOUNT_SPEND, totalSpending(accountState));
    var spendByMonth = Insight.of(TOTAL_ACCOUNT_SPEND_BY_MONTH, entriesGroupedByMonth(expenses));
    var spendByMonthExcludingInvestments = Insight.of(
        TOTAL_ACCOUNT_SPEND_BY_MONTH_EXCLUDING_INVESTMENTS,
        entriesGroupedByMonth(expensesExcludingInvestments));
    var basicCostsOfLiving = Insight.of(
        BASIC_COSTS_OF_LIVING,
        entriesGroupedByMonth(expensesExcludingInvestmentsAndWhims));
    var incomeByMonth = Insight.of(TOTAL_ACCOUNT_INCOME_BY_MONTH, entriesGroupedByMonth(incomes));

    List<Insight> insights = new LinkedList<>();
    if (includeCustom) {
      insights.addAll(List.of(costOfLiving, basicCostsOfLiving));
    }
    insights.addAll(List.of(averageSpendByMonthInsight, averageIncomeByMonthInsight,
        averageSavingsByMonthInsight, totalSpending, spendByMonth, incomeByMonth,
        spendByMonthExcludingInvestments));

    return new Insights(insights, buildMetadata(expenses));
  }

  protected Double averageEntriesValueByMonth(List<AccountEntry> accountEntries) {
    return entriesGroupedByMonth(accountEntries)
        .entrySet()
        .parallelStream()
        .mapToDouble(Entry::getValue)
        .average()
        .orElse(AVERAGE_OF_NO_VALUES);
  }

  private Double totalSpending(AccountState accountState) {
    return sumAccountEntries(accountState.expenses());
  }

  protected List<AccountEntry> filterOutExpenseType(List<AccountEntry> entries,
      List<String> excludedCategories) {
    return entries.stream().filter(entry -> !excludedCategories.contains(entry.category().name()))
        .toList();
  }

  protected Map<YearMonth, Double> entriesGroupedByMonth(List<AccountEntry> entries) {
    return new TreeMap<>(groupAccountEntriesYearMonth(entries)
        .entrySet()
        .parallelStream()
        .collect(Collectors.toMap(Entry::getKey,
            yearMonthAccountEntries -> sumAccountEntries(yearMonthAccountEntries.getValue()))));
  }

  protected InsightsMetadata buildMetadata(List<AccountEntry> expenses) {
    var expenseCategories = expenses.stream().map(expense -> expense.category().name()).sorted()
        .distinct()
        .toList();

    return new InsightsMetadata(expenseCategories);
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


}
