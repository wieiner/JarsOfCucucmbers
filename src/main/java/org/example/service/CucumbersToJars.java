package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CucumbersToJars {
    private static double volume = 0.0;
    public final static double standardJarVolume = 3.0;


/*
    public Flux<Cucumber> generateCucumberFlux(int cucumberCount) {

        return Flux.range(0, cucumberCount)
                .map(i -> {
                    return new Cucumber(0.063);
                });
    }
*/


    /*
                .map(cucumber -> {
                       volume += cucumber.getVolume();
                       return cucumber.getVolume();
                                })
     */
/*
    public static Flux<Jar> toJarFluxAdvanced(Flux<Cucumber> cucumberFlux) {

        return cucumberFlux
                .map(c -> {
                    volume += c.getVolume();
                    return c;
                })
                .bufferWhile(s -> {
                    return (volume <= standardJarVolume);
                })
                .map(s -> {

                });

    }
*/
}
