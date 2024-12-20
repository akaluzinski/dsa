package eu.kaluzinski.mf_importer.flow;

import static eu.kaluzinski.mf_importer.MonefyTestUtils.INVESTMENTS_CATEGORY;
import static eu.kaluzinski.mf_importer.MonefyTestUtils.RESOURCES_PATH;
import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_INCOME_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_SAVINGS_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.AVERAGE_SPEND_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_INCOME_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND_BY_MONTH;
import static eu.kaluzinski.mf_importer.emums.Metric.TOTAL_ACCOUNT_SPEND_BY_MONTH_EXCLUDING_INVESTMENTS;
import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.kaluzinski.mf_importer.reports.Insight;
import eu.kaluzinski.mf_importer.reports.Insights;
import eu.kaluzinski.mf_importer.reports.InsightsMetadata;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    var expectedInsightsAccount1 = new Insights(List.of(
        new Insight(AVERAGE_SPEND_BY_MONTH, 1956.3333333333333),
        new Insight(AVERAGE_INCOME_BY_MONTH, 10000.0),
        new Insight(AVERAGE_SAVINGS_BY_MONTH, 8043.666666666667),
        new Insight(TOTAL_ACCOUNT_SPEND, 5869.0),
        new Insight(TOTAL_ACCOUNT_SPEND_BY_MONTH,
            Map.of(YearMonth.parse("2024-01"), 2210.0,
                YearMonth.parse("2024-02"), 3519.0,
                YearMonth.parse("2024-11"), 140.0)),
        new Insight(TOTAL_ACCOUNT_INCOME_BY_MONTH,
            Map.of(YearMonth.parse("2024-01"), 10000.0)),
        new Insight(TOTAL_ACCOUNT_SPEND_BY_MONTH_EXCLUDING_INVESTMENTS,
            Map.of(YearMonth.parse("2024-01"), 2210.0,
                YearMonth.parse("2024-02"), 2519.0,
                YearMonth.parse("2024-11"), 140.0))
    ), new InsightsMetadata(List.of("Car", "Food", "Housing", "Internet", "Investments")));
    var expectedInsightsAccount2 = new Insights(List.of(
        new Insight(AVERAGE_SPEND_BY_MONTH, 1259.5),
        new Insight(AVERAGE_INCOME_BY_MONTH, 0.0),
        new Insight(AVERAGE_SAVINGS_BY_MONTH, -1259.5),
        new Insight(TOTAL_ACCOUNT_SPEND, 2519.0),
        new Insight(TOTAL_ACCOUNT_SPEND_BY_MONTH,
            Map.of(
                YearMonth.parse("2024-02"), 1319.0,
                YearMonth.parse("2024-05"), 1200.0)),
        new Insight(TOTAL_ACCOUNT_INCOME_BY_MONTH, emptyMap()),
        new Insight(TOTAL_ACCOUNT_SPEND_BY_MONTH_EXCLUDING_INVESTMENTS,
            Map.of(
                YearMonth.parse("2024-02"), 1319.0,
                YearMonth.parse("2024-05"), 1200.0))
    ), new InsightsMetadata(List.of("Food", "Internet")));

    //then
    assertEquals(expectedInsightsAccount1, insightsAccount1);
    assertEquals(expectedInsightsAccount2, insightsAccount2);
  }

  @Test
  void shouldPrepareCategoryReport() {
    //given
    var fileName = "complex_import_data.csv";
    var path = "%s%s".formatted(RESOURCES_PATH, fileName);

    //when
    var flow = FlowsProvider.categoryFlow(INVESTMENTS_CATEGORY);
    var insightsAccount1 = flow.importAndGenerate(path, "mBank");
    var insightsAccount2 = flow.importAndGenerate(path, "PKO");

    var expectedInsightsAccount1 = new Insights(List.of(
        new Insight(AVERAGE_SPEND_BY_MONTH, 1000.0),
        new Insight(TOTAL_ACCOUNT_SPEND_BY_MONTH,
            Map.of(YearMonth.parse("2024-02"), 1000.0))
    ), new InsightsMetadata(List.of(INVESTMENTS_CATEGORY)));
    var expectedInsightsAccount2 = new Insights(List.of(
        new Insight(AVERAGE_SPEND_BY_MONTH, 0.0),
        new Insight(TOTAL_ACCOUNT_SPEND_BY_MONTH, emptyMap())
    ), new InsightsMetadata(Collections.EMPTY_LIST));

    //then
    assertEquals(expectedInsightsAccount1, insightsAccount1);
    assertEquals(expectedInsightsAccount2, insightsAccount2);
  }
}