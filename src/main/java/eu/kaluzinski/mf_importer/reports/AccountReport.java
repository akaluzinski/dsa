package eu.kaluzinski.mf_importer.reports;

import eu.kaluzinski.mf_importer.model.AccountState;

public interface AccountReport {

  Insights create(AccountState accountState);
}
