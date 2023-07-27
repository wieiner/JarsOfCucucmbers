package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CucumbersToJarsImperativeStyle {
    public double standardJarVolume = 3.5;
    public double standardCucumberVolume = 10.6;

    public List<Double> jarVolumesForTestsOnly;
    private int counterOfJarVolumesForTestsOnly = -1;

    public double getStandardJarVolume() {

        if (jarVolumesForTestsOnly == null) {
            return (standardJarVolume);
        }

        if (jarVolumesForTestsOnly.isEmpty()) {
            return (standardJarVolume);
        }

        counterOfJarVolumesForTestsOnly++;
        if (counterOfJarVolumesForTestsOnly >= jarVolumesForTestsOnly.size()) {
            counterOfJarVolumesForTestsOnly = 0;
        }

        return jarVolumesForTestsOnly.get(counterOfJarVolumesForTestsOnly);
    }

    public List<Cucumber> generateCucumberList(int cucumberCount) {

        List<Cucumber> cucumberList = new ArrayList<>();

        for (int i = 0; i < cucumberCount; i++) {
            cucumberList.add(new Cucumber(standardCucumberVolume));
        }
        return cucumberList;
    }

    public void splitCucumberInListOfCucumbersPreparedForJars(List<Cucumber> cucumbersPreparedForJars, int cucumberId, double newCurrentVolumeCutFromOriginal) {

        if (cucumberId < 0 || cucumberId >= cucumbersPreparedForJars.size()) {
            return;
        }

        Cucumber cucumber = cucumbersPreparedForJars.get(cucumberId);
        double newCucumberVolume = cucumber.getVolume() - newCurrentVolumeCutFromOriginal;

        if (newCucumberVolume <= 0) {
            return;
        }

        cucumber.setVolume(newCurrentVolumeCutFromOriginal);
        cucumbersPreparedForJars.add(new Cucumber(newCucumberVolume));
    }

    public List<Jar> toJar(List<Cucumber> listOfCucumber) {
        List<Jar> jarList = new ArrayList<>();
        jarList.add(new Jar(getStandardJarVolume(), new ArrayList<>()));

        for (int i = 0; i < listOfCucumber.size(); i++) {
            addCucumberToJar(listOfCucumber, i, jarList.get(jarList.size() - 1));
            if (jarList.get(jarList.size() - 1).getFreeVolume() <= 0) {

                if (i < (listOfCucumber.size() - 1)) {
                    jarList.add(new Jar(getStandardJarVolume(), new ArrayList<>()));
                }

            }
        }

        return jarList;
    }

    private void addCucumberToJar(List<Cucumber> cucumberList, int id, Jar jar) {

        double freeVolumeInJar = jar.getFreeVolume();

        if (freeVolumeInJar == 0) {
            return;
        }

        if (freeVolumeInJar < cucumberList.get(id).getVolume()) {
            splitCucumberInListOfCucumbersPreparedForJars(cucumberList, id, freeVolumeInJar);
        }

        jar.getCucumberList().add(cucumberList.get(id));

    }

}
