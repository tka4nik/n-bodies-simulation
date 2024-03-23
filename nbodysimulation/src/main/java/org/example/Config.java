package org.example;

import java.util.ArrayList;

public class Config {
    private double delta_time;
    private long radius;
    private ArrayList<Body> bodies;
    public double getDelta_time() { return delta_time; }
    public long getRadius() { return radius; }
    public ArrayList<Body> getBodies() { return bodies; }
}
