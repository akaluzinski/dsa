package eu.kaluzinski.mf_importer.reports;

import eu.kaluzinski.mf_importer.emums.Metric;
import eu.kaluzinski.mf_importer.model.AccountEntry;
import eu.kaluzinski.mf_importer.model.AccountState;
import java.util.List;

public class BasicAccountReport implements AccountReport {

  @Override
  public Insights create(AccountState accountState) {
    var totalMonthSpending = new Insight(Metric.TOTAL_ACCOUNT_SPEND,
        totalMonthSpending(accountState));

    return new Insights(List.of(totalMonthSpending));
  }

  Double totalMonthSpending(AccountState accountState) {
    return accountState.expenses().stream()
        .mapToDouble(AccountEntry::amount)
        .sum();
  }

}
