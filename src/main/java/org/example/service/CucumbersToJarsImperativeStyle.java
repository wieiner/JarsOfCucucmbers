package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@AllArgsConstructor
@Getter
@Setter
public class CucumbersToJarsImperativeStyle {
    public final static double standardJarVolume = 3.0;

    public List<Cucumber> generateCucumberList(int cucumberCount) {

        List<Cucumber> lc = new ArrayList<Cucumber>(cucumberCount);
        Random rand = new Random();

        for (Cucumber c : lc) {
            c.setVolume(0.6);
        }

        return lc;
    }

    public List<Jar> toJar(List<Cucumber> lc) {
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
                j.cucumberList.clear();
                j.setVolume(standardJarVolume);
                //clear cucumber counter
                cucumbersVolume = 0.0;
            } else if (cucumbersVolume > j.getVolume()) {
                //remember exceed volume of cucumber
                cucumbersVolume = cucumbersVolume - j.getVolume();

                //correction of last cucumber volume in jar
                int size = j.getCucumberList().size();
                Cucumber c;
                if (size > 0) {
                    c =
                            j.getCucumberList().get(size - 1).divideCucumber(
                                    j.getCucumberList().get(size - 1).getVolume()
                                            - cucumbersVolume);
                    //add jar to jarlist
                    lj.add(j);
                    //setup new jar with half of cucumber
                    j.cucumberList.clear();
                    j.cucumberList.add(c);
                }
                j.setVolume(standardJarVolume);
                cucumbersVolume = 0.0;
            }

            System.out.println("the number" + i);
        }

        //add last jar to jarlist if they have an cucmbers
        if (j.getCucumberList().size() > 0) {
            lj.add(j);
        }

        return lj;
    }

}
