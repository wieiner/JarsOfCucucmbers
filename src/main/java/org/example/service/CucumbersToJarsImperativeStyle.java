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
    public double standardCucumberVolume = 0.6;

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

    public List<Cucumber> generateCucumberList(int cucumberCount) {

        List<Cucumber> cucumberList = new ArrayList<Cucumber>();

        for (int i = 0; i < cucumberCount; i++) {
            cucumberList.add(new Cucumber(standardCucumberVolume));
        }
        return cucumberList;
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

    public List<Jar> toJar(List<Cucumber> listOfCucumber) {
        List<Jar> jarList = new ArrayList<Jar>();
        jarList.add(new Jar(standardJarVolume, new ArrayList<Cucumber>()));

        for (int i = 0, idJar = 0; i < listOfCucumber.size(); i++) {

            addCucumberToJar(listOfCucumber, i, jarList.get(idJar));
            if (jarList.get(idJar).getFreeVolume() <= 0) {
                ++idJar;
                jarList.add(new Jar(standardJarVolume, new ArrayList<Cucumber>()));
            }
        }

        return jarList;
    }
}
