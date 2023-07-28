package org.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.example.service.Cucumber;
import org.example.service.Jar;
import org.example.service.PackerService;
import reactor.core.publisher.Flux;


public class PackerServiceImpl implements PackerService {
  public Flux<Jar> pack(Flux<Cucumber> cucumbers) {
    return cucumbers.reduceWith(ArrayList::new, this::addCucumberToJars)
        .flatMapMany(Flux::fromIterable);
  }
      private List<Jar> addCucumberToJars(List<Jar> jars, Cucumber cucumber) {
      while (cucumber.getVolume() > 0) {
        provideJarIfNeeded(jars);
        var jar = jars.get(jars.size() - 1);
        var volumeToAdd = Math.min(cucumber.getVolume(), getFreeSpace(jar));
        jar.getCucumberList().add(new Cucumber(volumeToAdd));
        cucumber.setVolume(cucumber.getVolume() - volumeToAdd);
      }
      return jars;
    }

    private void provideJarIfNeeded(List<Jar> jars) {
      var lastJar = jars.isEmpty() ? null : jars.get(jars.size() - 1);
      if (lastJar == null || getFreeSpace(lastJar) == 0) {
        jars.add(new Jar(getRandomVolume(),new ArrayList<>()));
      }
    }

    private double getFreeSpace(Jar jar) {
      return jar.getVolume() - jar.getCucumberList().stream().map(Cucumber::getVolume).reduce(0.0, Double::sum);
    }

    private int getRandomVolume() {
      return (int) (Math.random() * 10) + 1;
    }
  }
