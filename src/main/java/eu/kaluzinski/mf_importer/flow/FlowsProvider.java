package eu.kaluzinski.mf_importer.flow;

import eu.kaluzinski.mf_importer.CurrencyUnawareAccountDataMapper;
import eu.kaluzinski.mf_importer.CurrencyUnawareHeaderMapper;
import eu.kaluzinski.mf_importer.MonefyImporter;
import eu.kaluzinski.mf_importer.reports.BasicAccountReport;

public class FlowsProvider {

  public static BasicReportFlow basicFlow() {
    return new BasicReportFlow(
        new MonefyImporter(),
        new CurrencyUnawareAccountDataMapper(
            new CurrencyUnawareHeaderMapper()),
        new BasicAccountReport()
    );
  }

}
