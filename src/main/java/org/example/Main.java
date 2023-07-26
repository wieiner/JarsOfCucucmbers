package org.example;

import org.example.service.Cucumber;
import org.example.service.CucumbersToJarsImperativeStyle;
import org.example.service.Jar;

import java.util.List;

class SingletonService {

    public final CucumbersToJarsImperativeStyle cucumbersToJarsImperativeStyle = new CucumbersToJarsImperativeStyle();
    private final static SingletonService single_instance = new SingletonService();

    static SingletonService getInstance() {
        return single_instance;
    }
}

public class Main {
    public static void main(String[] args) {

        // Instantiating SingletonService class with variable singletonService
        SingletonService singletonService = SingletonService.getInstance();


        List<Cucumber> cucumberList = singletonService.cucumbersToJarsImperativeStyle
                        .generateCucumberList(10);

        List<Jar> jarList = singletonService.cucumbersToJarsImperativeStyle.toJar(cucumberList);

        for ( Jar j : jarList) {
            System.out.println("Создана банка объемом: "
                    + j.volume
                    + " из "
                    + j.cucumberList.size()
                    + " огурцов ");
        }

    }
}