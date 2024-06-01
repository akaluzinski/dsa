package eu.kaluzinski.mf_importer.flow;

import static eu.kaluzinski.mf_importer.MonefyTestUtils.RESOURCES_PATH;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND;
import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.kaluzinski.mf_importer.reports.Insight;
import eu.kaluzinski.mf_importer.reports.Insights;
import java.util.List;
import org.junit.jupiter.api.Test;

class BasicReportFlowTest {

  @Test
  void shouldPrepareBasicReport() {
    //given
    var fileName = "complex_import_data.csv";
    var path = "%s%s".formatted(RESOURCES_PATH, fileName);

    //when
    var flow = FlowsProvider.basicFlow();
    var insightsAccount1 = flow.importAndGenerate(path, "mBank");
    var insightsAccount2 = flow.importAndGenerate(path, "PKO");

    var expectedInsightsAccount1 = new Insights(List.of(new Insight(TOTAL_ACCOUNT_SPEND, 4869.0)));
    var expectedInsightsAccount2 = new Insights(List.of(new Insight(TOTAL_ACCOUNT_SPEND, 2519.0)));

    //then
    assertEquals(expectedInsightsAccount1, insightsAccount1);
    assertEquals(expectedInsightsAccount2, insightsAccount2);
  }
}