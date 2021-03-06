package de.noshak.plume_ex;

import java.io.File;
import java.io.IOException;

import io.github.plume.oss.Extractor;
import io.github.plume.oss.drivers.DriverFactory;
import io.github.plume.oss.drivers.GraphDatabase;
import io.github.plume.oss.drivers.TinkerGraphDriver;

public class Tinker {
  public static void main(String[] args) {
    System.out.println("Creating driver");
    try (TinkerGraphDriver driver = (TinkerGraphDriver) DriverFactory.invoke(GraphDatabase.TINKER_GRAPH)) {
      // Create the extractor with the driver and class root directory
      Extractor extractor = new Extractor(driver);
      File f = new File("./src/main/resources/Basic1.java");
      // Load the extractor with the directory of all the tests
      extractor.load(f);
      // Project the loaded classes to the graph database
      System.out.println("Projecting graph");
      extractor.project();
      // For the TinkerGraph hook, we can export this graph using the format and
      // directory specified by the extension
      driver.exportGraph("./graph.xml");
      System.out.println("Done!");
    } catch (IOException e) {
      System.out.println("Something went wrong! Details " + e.getMessage());
    }
  }
}
