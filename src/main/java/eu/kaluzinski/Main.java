package eu.kaluzinski;

import eu.kaluzinski.mf_importer.CurrencyUnawareAccountDataMapper;
import eu.kaluzinski.mf_importer.CurrencyUnawareHeaderMapper;
import eu.kaluzinski.mf_importer.MonefyImporter;
import eu.kaluzinski.mf_importer.flow.BasicReportFlow;
import eu.kaluzinski.mf_importer.reports.BasicAccountReport;
import eu.kaluzinski.mf_importer.reports.Insights;

public class Main {

  public static void main(String[] args) {
    Insights insights = new BasicReportFlow(
        new MonefyImporter(),
        new CurrencyUnawareAccountDataMapper(
            new CurrencyUnawareHeaderMapper()),
        new BasicAccountReport()
    ).importAndGenerate("src/main/resources/import.csv");

    System.out.println(insights);


  }
}
