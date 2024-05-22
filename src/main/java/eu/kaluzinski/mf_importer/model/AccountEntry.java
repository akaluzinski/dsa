package eu.kaluzinski.mf_importer.model;

import java.time.LocalDate;

public record AccountEntry(LocalDate date, Category category, Double amount, String description) {

  public AccountEntry toAbs() {
    return new AccountEntry(this.date, this.category, Math.abs(this.amount), this.description);
  }

}
