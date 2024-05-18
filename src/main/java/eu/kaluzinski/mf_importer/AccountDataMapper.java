package eu.kaluzinski.mf_importer;

import eu.kaluzinski.mf_importer.model.AccountState;
import java.util.List;

public interface AccountDataMapper {

  AccountState mapImportDataToAccountState(List<List<String>> rawImportData);

}
