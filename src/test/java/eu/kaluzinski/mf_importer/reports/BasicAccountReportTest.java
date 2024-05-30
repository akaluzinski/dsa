package eu.kaluzinski.mf_importer.reports;

import static eu.kaluzinski.mf_importer.MonefyTestUtils.currencyUnawareAccountEntry;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BasicAccountReportTest {

  private final BasicAccountReport basicAccountReport = new BasicAccountReport();

  @Test
  void shouldPrepareBasicAccountReport() {
    //when
    var insights = basicAccountReport.create(currencyUnawareAccountEntry);

    //then
    assertEquals(1, insights.insights().size());

    var totalAccountSpend = insights.insights().get(0);
    assertEquals(TOTAL_ACCOUNT_SPEND, totalAccountSpend.metric());
    assertEquals(2469.5, totalAccountSpend.value());
  }
}
