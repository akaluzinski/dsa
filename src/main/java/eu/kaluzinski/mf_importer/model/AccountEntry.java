package eu.kaluzinski.mf_importer.model;

import java.time.LocalDate;

public record AccountEntry(LocalDate date, Category category, Double amount, String description) {


}
