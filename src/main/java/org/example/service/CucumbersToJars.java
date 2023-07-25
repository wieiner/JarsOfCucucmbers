package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Flux;

import static reactor.core.publisher.Flux.range;

@AllArgsConstructor
@Getter
@Setter
public class CucumbersToJars
{
    public Flux<Cucucmber> generateCucumberFlux(int cucumberCount) {


        return Flux<Cucumber>.range(0, cucumberCount);

    }


    public static Flux<Jar> toJarFluxAdvanced (Flux<Cucumber> cucumberFlux)
    {
    return cucumberFlux
            .map(s -> { })
            .bufferWhile();

    }

}
