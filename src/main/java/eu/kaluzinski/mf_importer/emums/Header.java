package eu.kaluzinski.mf_importer.emums;

public enum Header {

  DATE("date"),
  ACCOUNT("account"),
  AMOUNT("amount"),
  CATEGORY("category"),
  CURRENCY("currency"),
  CONVERTED_AMOUNT("converted amount"),
  CONVERTED_CURRENCY("converted currency"),
  DESCRIPTION("description");


  private String name;

  Header(String name) {
    this.name = name;
  }


  public String getName() {
    return name;
  }


}
