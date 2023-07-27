package org.example.service;

import lombok.Setter;

import java.util.List;


@Setter
public class Jar {
    public double volume;
    public List<Cucumber> cucumberList;

    public Jar(double volume, List<Cucumber> cucumberList) {
        this.volume = volume;
        this.cucumberList = cucumberList;
    }

    public double getVolume() {
        return volume;
    }

    public double calculateTotalCucumbersVolume() {
        return cucumberList.stream().map(Cucumber::getVolume).reduce(0.0, Double::sum);
    }

    public double getFreeVolume() {
        return volume - calculateTotalCucumbersVolume();
    }


}
