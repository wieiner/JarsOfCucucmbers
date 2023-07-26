package org.example.service;

public class Cucumber {
    public Cucumber(double volume) {
        this.volume = volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    private double volume;

    public Cucumber divideCucumber(double newCurrentVolumeCuttedFromOriginal)
    {
        double v = volume - newCurrentVolumeCuttedFromOriginal;
        volume = newCurrentVolumeCuttedFromOriginal;
        if (v < 0) {
            return null;
        }
        return new Cucumber(v);
    }
}
