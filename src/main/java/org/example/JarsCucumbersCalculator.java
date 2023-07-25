package org.example;

import reactor.core.publisher.Flux;

import java.util.logging.Logger;

public class JarsCucumbersCalculator {
    static Logger logger = Logger.getLogger("test");

    private static final double cucumberVolume = 1.0;
    private static final double jarVolume = 4.0;

    private static  int cntr = 0 ;

    public Flux<Double> generateCucumberFlux(int cucumberCount) {
        System.out.println("всего в потоке " +
                cucumberCount +
                " огурцов , обьемом " +
                cucumberVolume + " обьем 1 банки "
                + jarVolume);

        return Flux.range(0, cucumberCount)
                .map(i -> cucumberVolume);
    }



    public Flux<Double> toJarFlux2(Flux<Double> cucumberFlux) {
        return cucumberFlux
                .reduce(0.0, (currentVolume, volume) -> {
                    double newVolume = currentVolume + volume;
                    if (newVolume >= jarVolume) {
                        newVolume -= jarVolume;
                    }
                    return newVolume;
                })
                .flux();
    }


    public Flux<Double> toJarFluxAdvanced(Flux<Double> cucumberFlux) {


        return cucumberFlux
                .map(volume -> {
                    cntr++;
                    return volume;
                })
                .bufferWhile(v -> {
                    return(cntr <= (jarVolume/cucumberVolume));
                })
                .map(
                        jar ->
                        {
                            cntr =0;
                            System.out.println("count of elements = " + jar.stream().count() );

                            return jar.stream()
                                    .reduce(Double::sum)
                                    .orElse(jarVolume % cucumberVolume);
                        }
                );
    }


    public Flux<Double> toJarFlux(Flux<Double> cucumberFlux) {

        final double[] currentVolume = {0};

        Flux<Double> jarsFlux = cucumberFlux
                .flatMap(volume -> {
                    System.out.println(" volume = " + currentVolume[0]);

                    if ((currentVolume[0] + volume) < jarVolume) {
                        currentVolume[0] += volume;
                        System.out.println(" volume emty last = " + currentVolume[0]);
                        return Flux.empty();
                    } else {
                        double remainder = volume - (jarVolume - currentVolume[0]);
                        currentVolume[0] = remainder;
                        System.out.println(" volume just last = " + currentVolume[0]);
                        return Flux.just(jarVolume);
                    }

                }).concatWith(
                        Flux.just(currentVolume[0])
                );





        return jarsFlux;

    }


}


