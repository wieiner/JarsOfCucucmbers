package org.example;

import reactor.core.publisher.Flux;

class SingletonService {

    public final JarsCucumbersCalculator cucumber = new JarsCucumbersCalculator();
    private final static SingletonService single_instance = new SingletonService();

    static SingletonService getInstance() {
        return single_instance;
    }
}

    public class Main {
        public static void main(String[] args) {

            // Instantiating SingletonService class with variable singletonService
            SingletonService singletonService = SingletonService.getInstance();


            Flux<Double> cucumberFlux = singletonService.cucumber.generateCucumberFlux(10);
            Flux<Double> jarFlux = singletonService.cucumber.toJarFluxAdvanced(cucumberFlux);

            jarFlux.subscribe(jarVolume ->
                    System.out.println("Создана банка объемом: " + jarVolume)
            );


            System.out.println("Hello world!");
        }
    }