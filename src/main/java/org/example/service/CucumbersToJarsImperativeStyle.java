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
    public double standardCucumberVolume = 5.6;

    public int splitCucumberInListOfCucumbersPreparedForJars(List<Cucumber> cucumbersPreparedForJars, int cucumberId, double newCurrentVolumeCutFromOriginal) {

        if (cucumberId < 0 || cucumberId >= cucumbersPreparedForJars.size()) {
            return -1;
        }

        Cucumber cucumber = cucumbersPreparedForJars.get(cucumberId);
        double newCucumberVolume = cucumber.getVolume() - newCurrentVolumeCutFromOriginal;

        if (newCucumberVolume <= 0) {
            return -1;
        }

        cucumber.setVolume(newCurrentVolumeCutFromOriginal);
        cucumbersPreparedForJars.add(new Cucumber(newCucumberVolume));
        return cucumbersPreparedForJars.size() - 1;
    }

    public List<Cucumber> generateCucumberList(int cucumberCount) {

        List<Cucumber> cucumberList = new ArrayList<Cucumber>();

        for (int i = 0; i < cucumberCount; i++) {
            cucumberList.add(new Cucumber(standardCucumberVolume));
        }
        return cucumberList;
    }

    private boolean addCucumberToJar(List<Cucumber> cucumberList, int id, Jar jar)
    {
        double freeVolumeInJar = jar.getFreeVolume();

        if ( freeVolumeInJar < cucumberList.get(id).getVolume())   {
            splitCucumberInListOfCucumbersPreparedForJars(cucumberList, id , freeVolumeInJar);
        }
        return false;
    }

    public List<Jar> toJar(List<Cucumber> listOfCucumber) {
        List<Jar> jarList = new ArrayList<Jar>();


        for (int i=0; i< listOfCucumber.size(); i++ ) {
            Jar jar = new Jar(standardJarVolume, new ArrayList<Cucumber>() );
            addCucumberToJar(listOfCucumber, i, );
        }

        return jarList;
    }

    public List<Jar> toJar2(List<Cucumber> lc) {
        List<Jar> lj = new ArrayList<Jar>(0);

        Jar j = new Jar(standardJarVolume, new ArrayList<Cucumber>(0));


        double cucumbersVolume = 0.0;

        for (int i = 0; i < lc.size(); i++) {
            cucumbersVolume += lc.get(i).getVolume();
            j.cucumberList.add(lc.get(i));
            if (cucumbersVolume == j.getVolume()) {

                //add jar to jarlist
                lj.add(j);
                //setup new empty jar
                j = new Jar(standardJarVolume, new ArrayList<>());
                //clear cucumber counter
                cucumbersVolume = 0.0;
            } else if (cucumbersVolume > j.getVolume()) {
                //remember exceed volume of cucumber
                cucumbersVolume = cucumbersVolume - j.getVolume();

                //correction of last cucumber volume in jar
                int size = j.getCucumberList().size();
                Cucumber c;
                if (size > 0) {
                    c = j.getCucumberList().get(size - 1).divideCucumber(
                            j.getCucumberList().get(size - 1).getVolume()
                                    - cucumbersVolume);
                    //add jar to jarlist
                    lj.add(j);
                    //setup new jar with half of cucumber
                    j = new Jar(standardJarVolume, new ArrayList<>());
                    j.cucumberList.add(c);
                }
                j.setVolume(standardJarVolume);
            }
        }
        //add last jar to jarlist if they have a cucumbers
        if (j.getCucumberList().size() > 0) {
            lj.add(j);
        }
        return lj;
    }

}
