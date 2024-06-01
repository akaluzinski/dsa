package eu.kaluzinski;

import static eu.kaluzinski.mf_importer.flow.FlowsProvider.basicFlow;

public class Main {

  public static void main(String[] args) {
    var insights = basicFlow().importAndGenerate("src/main/resources/import.csv", "mBank");

    System.out.println(insights);


  }
}
