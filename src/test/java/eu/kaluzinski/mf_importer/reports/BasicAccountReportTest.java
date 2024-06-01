package eu.kaluzinski.mf_importer.reports;

import static eu.kaluzinski.mf_importer.MonefyTestUtils.currencyUnawareAccountEntry;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND_BY_MONTH;
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
        new Insight(TOTAL_ACCOUNT_SPEND, 3669.6),
        new Insight(TOTAL_ACCOUNT_SPEND_BY_MONTH,
            Map.of(YearMonth.parse("2024-01"), 2210.0, YearMonth.parse("2024-02"), 1459.6))
    ), insights.insights());
  }
}
