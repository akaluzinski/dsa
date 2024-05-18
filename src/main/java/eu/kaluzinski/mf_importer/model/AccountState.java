package eu.kaluzinski.mf_importer.model;

import java.util.List;

public record AccountState(List<AccountEntry> incomes, List<AccountEntry> expenses) {

}
