package org.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CucumbersToJarsImperativeStyleTest {

  private CucumbersToJarsImperativeStyle cToJars;

  @Test
  void toJarCheckAmountOfJarsByGiantCucumbersVolumes() {
    Cucumber c1 = new Cucumber(7);
    Cucumber c2 = new Cucumber(0.3);
    Cucumber c3 = new Cucumber(4);
    Cucumber c4 = new Cucumber(0.4);

    List<Cucumber> cucumberList = new ArrayList<>();
    cucumberList.add(c1);
    cucumberList.add(c2);
    cucumberList.add(c3);
    cucumberList.add(c4);

    cToJars = new CucumbersToJarsImperativeStyle();

    // List of jars with different volumes
    cToJars.jarVolumesForTestsOnly = new ArrayList<>(2);
    cToJars.jarVolumesForTestsOnly.add(1.0);
    cToJars.jarVolumesForTestsOnly.add(2.0);

    //processing cucumbers to jars...
    List<Jar> jars = cToJars.toJar(cucumberList);

    Assertions.assertEquals(8,
        jars.size(), "wrong amount of jars");

  }

  @Test
  void toJarCheckVolumesOfCucumbersInJars() {
    Cucumber c1 = new Cucumber(15);
    Cucumber c2 = new Cucumber(3);
    Cucumber c3 = new Cucumber(17);

    List<Cucumber> cucumberList = new ArrayList<>();
    cucumberList.add(c1);
    cucumberList.add(c2);
    cucumberList.add(c3);

    cToJars = new CucumbersToJarsImperativeStyle();

    // List of jars with different volumes
    cToJars.jarVolumesForTestsOnly = new ArrayList<>(3);
    cToJars.jarVolumesForTestsOnly.add(21.0);
    cToJars.jarVolumesForTestsOnly.add(2.0);
    cToJars.jarVolumesForTestsOnly.add(20.0);

    //processing cucumbers to jars...
    List<Jar> jars = cToJars.toJar(cucumberList);

    Assertions.assertArrayEquals(new double[]{15.0, 3.0, 3.0},
        jars.get(0).getCucumberList().stream()
            .mapToDouble(Cucumber::getVolume).toArray());

    Assertions.assertArrayEquals(new double[]{2.0}, jars.get(1).getCucumberList().stream()
        .mapToDouble(Cucumber::getVolume).toArray());

    Assertions.assertArrayEquals(new double[]{12.0}, jars.get(2).getCucumberList().stream()
        .mapToDouble(Cucumber::getVolume).toArray());


  }

  @Test
  void toJarCheckTotalVolumesOfCucumbersInJarsWithDifferentVolumes() {
    // List of cucumbers with different volumes
    Cucumber c1 = new Cucumber(5);
    Cucumber c2 = new Cucumber(10);
    Cucumber c3 = new Cucumber(7);

    List<Cucumber> cucumberList = new ArrayList<>();
    cucumberList.add(c1);
    cucumberList.add(c2);
    cucumberList.add(c3);

    cToJars = new CucumbersToJarsImperativeStyle();

    // List of jars with different volumes
    cToJars.jarVolumesForTestsOnly = new ArrayList<>(3);
    cToJars.jarVolumesForTestsOnly.add(1.0);
    cToJars.jarVolumesForTestsOnly.add(20.0);
    cToJars.jarVolumesForTestsOnly.add(2.0);

    //processing cucumbers to jars...
    List<Jar> jars = cToJars.toJar(cucumberList);

    assertEquals(3, jars.size(), "amount of jars is not correct");

    assertEquals(1.0, jars.get(0).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of first jar and total volume of cucumbers is not equal");
    assertEquals(20.0,
        jars.get(1).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of second jar and total volume of cucumbers is not equal");
    assertEquals(1.0, jars.get(2).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "total volume of cucumbers in third jar is incorrect");

  }

  @Test
  void toJarCheckTotalVolumeInJarsWithDifferentVolumesOfJarsAndCucumbers() {

    Cucumber c1 = new Cucumber(5);
    Cucumber c2 = new Cucumber(1);
    Cucumber c3 = new Cucumber(6);
    Cucumber c4 = new Cucumber(2);

    List<Cucumber> cucumberList = new ArrayList<>();
    cucumberList.add(c1);
    cucumberList.add(c2);
    cucumberList.add(c3);
    cucumberList.add(c4);

    cToJars = new CucumbersToJarsImperativeStyle();

    // List of jars with different volumes
    //if no jar volume not available in list for jar it begins from index 0
    // 3.0--2.0--3.0--2.0--...,
    cToJars.jarVolumesForTestsOnly = new ArrayList<>(2);
    cToJars.jarVolumesForTestsOnly.add(3.0);
    cToJars.jarVolumesForTestsOnly.add(2.0);

    //processing cucumbers to jars...
    List<Jar> jars = cToJars.toJar(cucumberList);

    // Check the size of the jars list
    assertEquals(6, jars.size());

    // check the total volume of cucumbers in the first jar
    double totalVolumeInFirstJar = jars.get(0).getCucumberList().stream()
        .mapToDouble(Cucumber::getVolume).sum();
    assertEquals(3.0, totalVolumeInFirstJar, "total volume of cucumbers in first jar is wrong");

    // check the total volume of cucumbers in the second jar
    double totalVolumeInSecondJar = jars.get(1).getCucumberList().stream()
        .mapToDouble(Cucumber::getVolume).sum();
    assertEquals(2.0, totalVolumeInSecondJar, "total volume of cucumbers in second jar is wrong");

    // check the total volume of cucumbers in the third jar
    double totalVolumeInThirdJar = jars.get(2).getCucumberList().stream()
        .mapToDouble(Cucumber::getVolume).sum();
    assertEquals(3.0, totalVolumeInThirdJar, "total volume of cucumbers in third jar is wrong");

    // check the total volume of cucumbers in the fourth jar
    double totalVolumeInFourthJar = jars.get(3).getCucumberList().stream()
        .mapToDouble(Cucumber::getVolume).sum();
    assertEquals(2.0, totalVolumeInFourthJar, "total volume of cucumbers in fourth' jar is wrong");

    // check the total volume of cucumbers in the five jar
    double totalVolumeInFiveJar = jars.get(4).getCucumberList().stream()
        .mapToDouble(Cucumber::getVolume).sum();
    assertEquals(3.0, totalVolumeInFiveJar, "total volume of cucumbers in five jar is wrong");

    // check the total volume of cucumbers in the six jar
    double totalVolumeInSixJar = jars.get(5).getCucumberList().stream()
        .mapToDouble(Cucumber::getVolume).sum();
    assertEquals(1.0, totalVolumeInSixJar, "total volume of cucumbers in six' jar is wrong");
  }

  @Test
  void toJarCheckTotalVolumeInJars() {

    Cucumber c1 = new Cucumber(5);
    Cucumber c2 = new Cucumber(10);
    Cucumber c3 = new Cucumber(7);

    List<Cucumber> cucumberList = new ArrayList<>();
    cucumberList.add(c1);
    cucumberList.add(c2);
    cucumberList.add(c3);

    cToJars = new CucumbersToJarsImperativeStyle();

    // Setting standard volume of each Jar
    cToJars.standardJarVolume = 10;

    //processing cucumbers to jars...
    List<Jar> jars = cToJars.toJar(cucumberList);

    // Check the size of the jars list
    assertEquals(3, jars.size());

    // check the total volume of cucumbers in the first jar (Modern stream approach)
    double totalVolumeInFirstJar = jars.get(0).getCucumberList().stream()
        .mapToDouble(Cucumber::getVolume).sum();
    assertEquals(10, totalVolumeInFirstJar);

    // check the total volume of cucumbers in the second jar (Modern stream approach)
    double totalVolumeInSecondJar = jars.get(1).getCucumberList().stream()
        .mapToDouble(Cucumber::getVolume).sum();
    assertEquals(10, totalVolumeInSecondJar);

    // check the total volume of cucumbers in the second jar (Modern stream approach)
    double totalVolumeInThirdJar = jars.get(2).getCucumberList().stream()
        .mapToDouble(Cucumber::getVolume).sum();
    assertEquals(2, totalVolumeInThirdJar);

  }

  @Test
  void toJarCheckCaseWhenTwoBigCucumbersFillsTreeAndHalfJars() {
    cToJars = new CucumbersToJarsImperativeStyle();

    cToJars.standardJarVolume = 3.0;
    cToJars.standardCucumberVolume = 5.25;

    //processing 1 giant cucumber to jars...
    List<Jar> jars = cToJars.toJar(cToJars.generateCucumberList(2));

    assertEquals(4, jars.size(),
        "wrong amount of jars");

    assertNotEquals(0, jars.get(0).getCucumberList().size(),
        "amount of cucumbers in first jar is wrong");
    assertEquals(3.0, jars.get(0).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of first jar and total volume of cucumber is not equal");

    assertNotEquals(0, jars.get(1).getCucumberList().size(),
        "amount of cucumbers in second jar is wrong");
    assertEquals(3.0, jars.get(1).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of second jar and total volume of cucumber is not equal");

    assertNotEquals(0, jars.get(2).getCucumberList().size(),
        "amount of cucumbers in third jar is wrong");
    assertEquals(3.0, jars.get(2).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of third jar and total volume of cucumber is not equal");

    assertNotEquals(0, jars.get(3).getCucumberList().size(),
        "third jar is empty");
    assertEquals(1.5, jars.get(3).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of third jar and total volume of cucumber is not equal");

  }

  @Test
  void toJarCheckCaseWhenOneBigCucumberFillsTreeAndHalfJars() {
    cToJars = new CucumbersToJarsImperativeStyle();

    cToJars.standardJarVolume = 3.0;
    cToJars.standardCucumberVolume = 10.5;

    //processing 1 giant cucumber to jars...
    List<Jar> jars = cToJars.toJar(cToJars.generateCucumberList(1));

    assertEquals(4, jars.size(),
        "wrong amount of jars");

    assertEquals(1.0, jars.get(0).getCucumberList().size(),
        "amount of cucumbers in first jar is wrong");
    assertEquals(3.0, jars.get(0).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of first jar and total volume of cucumber is not equal");

    assertEquals(1.0, jars.get(1).getCucumberList().size(),
        "amount of cucumbers in second jar is wrong");
    assertEquals(3.0, jars.get(1).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of second jar and total volume of cucumber is not equal");

    assertEquals(1.0, jars.get(2).getCucumberList().size(),
        "amount of cucumbers in third jar is wrong");
    assertEquals(3.0, jars.get(2).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of third jar and total volume of cucumber is not equal");

    assertEquals(1.0, jars.get(3).getCucumberList().size(),
        "amount of cucumbers in third jar is wrong");
    assertEquals(1.5, jars.get(3).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of third jar and total volume of cucumber is not equal");

  }

  @Test
  void toJarCheckTotalVolumesOfCucumbersInJarsAndAmountOfJars() {
    cToJars = new CucumbersToJarsImperativeStyle();

    cToJars.standardJarVolume = 3.0;
    cToJars.standardCucumberVolume = 0.6;

    //processing 10 cucumbers to jars...
    List<Jar> jars = cToJars.toJar(cToJars.generateCucumberList(10));

    assertEquals(2.0, jars.size(),
        "wrong amount of jars");

    assertEquals(5.0, jars.get(0).getCucumberList().size(),
        "amount of cucumbers in first jar is wrong");
    assertEquals(3.0, jars.get(0).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of first jar and total volume of cucumbers is not equal");

    assertEquals(5.0, jars.get(0).getCucumberList().size(),
        "amount of cucumbers in second jar is wrong");
    assertEquals(3.0, jars.get(0).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
        "volume of second jar and total volume of cucumbers is not equal");
  }
}