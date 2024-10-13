package eu.kaluzinski.mf_importer.reports;

import static eu.kaluzinski.mf_importer.MonefyTestUtils.currencyUnawareAccountEntry;
import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_INCOME_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_SAVINGS_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_SPEND_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_INCOME_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND_BY_MONTH_EXCLUDING_INVESTMENTS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class BasicAccountReportTest {

  private final BasicAccountReport basicAccountReport = new BasicAccountReport();

  @Test
  void shouldPrepareBasicAccountReport() {
    //when
    var insights = basicAccountReport.create(currencyUnawareAccountEntry);

    //then
    assertEquals(List.of(
        new Insight(AVERAGE_SPEND_BY_MONTH, 1834.8),
        new Insight(AVERAGE_INCOME_BY_MONTH, 10000.0),
        new Insight(AVERAGE_SAVINGS_BY_MONTH, 8165.2),
        new Insight(TOTAL_ACCOUNT_SPEND, 3669.6),
        new Insight(TOTAL_ACCOUNT_SPEND_BY_MONTH,
            Map.of(YearMonth.parse("2024-01"), 2210.0, YearMonth.parse("2024-02"), 1459.6)),
        new Insight(TOTAL_ACCOUNT_INCOME_BY_MONTH,
            Map.of(YearMonth.parse("2024-01"), 10000.0)),
        new Insight(TOTAL_ACCOUNT_SPEND_BY_MONTH_EXCLUDING_INVESTMENTS,
            Map.of(YearMonth.parse("2024-01"), 2210.0, YearMonth.parse("2024-02"), 1459.6))
    ), insights.insights());
  }
}
