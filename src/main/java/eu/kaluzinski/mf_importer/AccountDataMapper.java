package eu.kaluzinski.mf_importer;

import eu.kaluzinski.mf_importer.model.AccountState;
import java.util.List;

public interface AccountDataMapper {

  AccountState mapImportDataToAccountState(String accountName, List<List<String>> rawImportData);

}
