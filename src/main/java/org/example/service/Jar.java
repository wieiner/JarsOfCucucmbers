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

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public List<Cucumber> getCucumberList() {
        return cucumberList;
    }

    public void setCucumberList(List<Cucumber> cucumberList) {
        this.cucumberList = cucumberList;
    }

}
