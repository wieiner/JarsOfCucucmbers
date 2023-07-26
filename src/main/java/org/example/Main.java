package org.example;

import org.example.service.Cucumber;
import org.example.service.CucumbersToJarsImperativeStyle;
import org.example.service.Jar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class SingletonService {

    public final CucumbersToJarsImperativeStyle cucumbersToJarsImperativeStyle = new CucumbersToJarsImperativeStyle();
    private final static SingletonService single_instance = new SingletonService();

    static SingletonService getInstance() {
        return single_instance;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        int numOfCucumbers = 3;
        // Instantiating SingletonService class with variable singletonService
        SingletonService singletonService = SingletonService.getInstance();


        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.print("Enter number of cucumbers " );
        String s = reader.readLine();
        numOfCucumbers = Integer.parseInt(s);

        List<Cucumber> cucumberList = singletonService.cucumbersToJarsImperativeStyle
                .generateCucumberList(numOfCucumbers);

        List<Jar> jarList = singletonService.cucumbersToJarsImperativeStyle.toJar(cucumberList);

        System.out.println("na whode " +numOfCucumbers+ " standartnych ogurzow objemom "+ singletonService.cucumbersToJarsImperativeStyle.standardCucumberVolume+" prystupaem k sozdaniju banok");

        for ( Jar j : jarList) {
            System.out.println("Создана банка объемом: "
                    + j.volume
                    + " из "
                    + j.cucumberList.size()
                    + " огурцов ");

            int i=0;
            double v=0.0;
            for ( Cucumber c : j.getCucumberList()) {
                i++; v+= c.getVolume();
                System.out.println(" огурец: "
                        + i
                        + " обьемом "
                        + c.getVolume()
                        + " итого обьем  " + v);

            }
            System.out.println(" итого тотальный обьем по огурцам: " + v + " а обьем банки " + j.getVolume() );
            System.out.println("");
        }

    }
}