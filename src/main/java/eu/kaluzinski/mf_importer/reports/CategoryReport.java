package eu.kaluzinski.mf_importer.reports;

import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_SPEND_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND_BY_MONTH;

import eu.kaluzinski.mf_importer.model.AccountEntry;
import eu.kaluzinski.mf_importer.model.AccountState;
import java.util.List;

public class CategoryReport extends BasicAccountReport {

  private final String category;

  public CategoryReport(String category) {
    super();
    this.category = category;
  }

  @Override
  public Insights create(AccountState accountState) {
    var expenses = accountState.expenses();
    var categoryExpenses = selectCategory(expenses, List.of(category));
    var averageSpendByMonthInsight = Insight.of(AVERAGE_SPEND_BY_MONTH,
        averageEntriesValueByMonth(categoryExpenses));
    var categorySpendByMonthInsight = Insight.of(
        TOTAL_ACCOUNT_SPEND_BY_MONTH, entriesGroupedByMonth(categoryExpenses));

    return new Insights(List.of(averageSpendByMonthInsight, categorySpendByMonthInsight),
        buildMetadata(categoryExpenses));
  }

  private List<AccountEntry> selectCategory(List<AccountEntry> entries,
      List<String> selectedCategory) {
    return entries.stream().filter(entry -> selectedCategory.contains(entry.category().name()))
        .toList();
  }


}
