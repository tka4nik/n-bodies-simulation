package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");

        Simulation simulation = new Simulation(100, 0.1, "file");
        simulation.run();
    }
}
