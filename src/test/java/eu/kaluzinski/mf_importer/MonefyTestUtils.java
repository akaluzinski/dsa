package eu.kaluzinski.mf_importer;

import eu.kaluzinski.mf_importer.model.AccountEntry;
import eu.kaluzinski.mf_importer.model.AccountState;
import eu.kaluzinski.mf_importer.model.Category;
import java.time.LocalDate;
import java.util.List;

public class MonefyTestUtils {

  public static final String RESOURCES_PATH = "src/test/resources/";
  public static List<String> IMPORT_HEADER = List.of("date", "account", "category", "amount",
      "currency", "converted amount", "currency", "description");


  public static List<AccountEntry> accountIncomeState = List.of(
      new AccountEntry(LocalDate.of(2024, 1, 1), new Category("Salary"), 10000.0, "")
  );
  public static List<AccountEntry> accountOutcomeState = List.of(
      new AccountEntry(LocalDate.of(2024, 1, 16), new Category("Housing"), 2210.0, "Rent"),
      new AccountEntry(LocalDate.of(2024, 2, 1), new Category("Internet"), 119.0, "UPC"),
      new AccountEntry(LocalDate.of(2024, 2, 17), new Category("Car"), 140.5, "Tires change"),
      new AccountEntry(LocalDate.of(2024, 2, 19), new Category("Food"), 1200.1, "Monthly food")
  );

  public static AccountState currencyUnawareAccountEntry = new AccountState(accountIncomeState,
      accountOutcomeState);

}
