package org.example;

import org.example.service.Cucumber;
import org.example.service.CucumbersToJarsImperativeStyle;
import org.example.service.Jar;

import java.util.List;

class SingletonService {

  public final CucumbersToJarsImperativeStyle cucumbersToJarsImperativeStyle = new CucumbersToJarsImperativeStyle();
  private final static SingletonService single_instance = new SingletonService();

  static SingletonService getInstance() {
    return single_instance;
  }
}

public class Main {

  public static void main(String[] args) {

    int numOfCucumbers = 2;
    SingletonService singletonService = SingletonService.getInstance();

    singletonService.cucumbersToJarsImperativeStyle.standardJarVolume = 3.0;
    singletonService.cucumbersToJarsImperativeStyle.standardCucumberVolume = 5.25;

    List<Cucumber> cucumberList = singletonService.cucumbersToJarsImperativeStyle
        .generateCucumberList(numOfCucumbers);

    List<Jar> jarList = singletonService.cucumbersToJarsImperativeStyle.toJar(cucumberList);

    System.out.println("на входе " + numOfCucumbers + " огурцов, обьемом "
        + singletonService.cucumbersToJarsImperativeStyle.standardCucumberVolume
        + " приступаем к созданию банок ");

    for (Jar j : jarList) {
      System.out.println("Создана банка объемом: "
          + j.volume
          + " из "
          + j.cucumberList.size()
          + " огурцов ");

      int i = 0;
      double v = 0.0;
      for (Cucumber c : j.getCucumberList()) {
        i++;
        v += c.getVolume();
        System.out.println(" огурец: "
            + i
            + " обьемом "
            + c.getVolume()
            + " итого обьем  " + v);

      }
      System.out.println(
          " итого тотальный обьем по огурцам: " + v + " а обьем банки " + j.getVolume());
      System.out.println();
    }

  }
}