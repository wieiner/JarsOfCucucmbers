package org.example.service;

import lombok.Setter;

import java.util.List;


@Setter
public class Jar {
    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double volume;

    public List<Cucumber> getCucumberList() {
        return cucumberList;
    }

    public void setCucumberList(List<Cucumber> cucumberList) {
        this.cucumberList = cucumberList;
    }

    public List<Cucumber> cucumberList;

    public double getVolume() {
        return volume;
    }

    public Jar(double volume, List<Cucumber> cucumberList) {
        this.volume = volume;
        this.cucumberList = cucumberList;
    }
}
