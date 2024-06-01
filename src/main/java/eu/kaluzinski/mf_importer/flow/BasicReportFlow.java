package eu.kaluzinski.mf_importer.flow;

import eu.kaluzinski.mf_importer.AccountDataMapper;
import eu.kaluzinski.mf_importer.MonefyImporter;
import eu.kaluzinski.mf_importer.reports.AccountReport;
import eu.kaluzinski.mf_importer.reports.Insights;

public class BasicReportFlow {

  private MonefyImporter monefyImporter;
  private AccountDataMapper accountDataMapper;
  private AccountReport accountReport;

  public BasicReportFlow(MonefyImporter monefyImporter, AccountDataMapper accountDataMapper,
      AccountReport accountReport) {
    this.monefyImporter = monefyImporter;
    this.accountDataMapper = accountDataMapper;
    this.accountReport = accountReport;
  }

  public Insights importAndGenerate(String fileName) {
    var rawImportEntries = monefyImporter.rawImportMonefyFile(fileName);
    var accountState = accountDataMapper.mapImportDataToAccountState(rawImportEntries);
    return accountReport.create(accountState);
  }

}
