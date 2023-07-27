package org.example.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CucumbersToJarsImperativeStyleTest {
    private CucumbersToJarsImperativeStyle cToJars;


    @Test
    void generateCucumberList() {
    }

    @Test
    void toJarCheckTotalVolumeInJarsByVariableVolumeOfJarsAndCucumbers() {
        // List of cucumbers with different volumes
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
        cToJars.jarVolumesForTestsOnly = new ArrayList<>(3);
        cToJars.jarVolumesForTestsOnly.add(1.0);
        cToJars.jarVolumesForTestsOnly.add(20.0);
        cToJars.jarVolumesForTestsOnly.add(2.0);



        //processing cucumbers to jars...
        List<Jar> jars = cToJars.toJar(cucumberList);


            assertEquals( 1.0, jars.get(0).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum() ,
                    "volume of first jar and total volume of cucumbers is not equal");
            assertEquals( 20.0, jars.get(1).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
                     "volume of second jar and total volume of cucumbers is not equal");
            assertEquals(1.0, jars.get(2).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum(),
                    "total volume of cucumbers in third jar is incorrect");

    }

    @Test
    void toJarCheckTotalVolumeInJars() {


        Cucumber c1 = new Cucumber(5);
        Cucumber c2 = new Cucumber(10);
        Cucumber c3 = new Cucumber(7);

        List<Cucumber> lc = new ArrayList<>();
        lc.add(c1);
        lc.add(c2);
        lc.add(c3);


        cToJars = new CucumbersToJarsImperativeStyle();

        // Setting standard volume of each Jar
        cToJars.standardJarVolume = 10;


        //processing cucumbers to jars...
        List<Jar> jars = cToJars.toJar(lc);

        // Check the size of the jars list
        assertEquals(3, jars.size());

        // check the total volume of cucumbers in the first jar (Modern stream approach)
        double totalVolumeInFirstJar = jars.get(0).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum();
        assertEquals(10, totalVolumeInFirstJar);

        // check the total volume of cucumbers in the second jar (Modern stream approach)
        double totalVolumeInSecondJar = jars.get(1).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum();
        assertEquals(10, totalVolumeInSecondJar);

        // check the total volume of cucumbers in the second jar (Modern stream approach)
        double totalVolumeInThirdJar = jars.get(2).getCucumberList().stream().mapToDouble(Cucumber::getVolume).sum();
        assertEquals(2, totalVolumeInThirdJar);

    }
}