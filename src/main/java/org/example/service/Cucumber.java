package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Cucumber {
    public void setVolume(double volume) {
        this.volume = volume;
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
