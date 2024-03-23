package org.example;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Arrays;

/**
 * Class that describes a physics body.
 */
public class Body {
    double[] position = {0, 0};
    double[] velocity = {0, 0};
    double mass = 1;
    String colour = "black";
    public static final double GCONSTANT = 6.67 * Math.pow(10,-11);

    public Body() {}
    public Body(double[] position, double[] velocity, double mass) {
        this.position[0] = position[0];
        this.position[1] = position[1];
        this.velocity[0] = velocity[0];
        this.velocity[1] = velocity[1];
        this.mass = mass;
    }

    /**
     * Calculates force between this body and other body. Uses Newton's law of gravity.
     * @return force between this body and passed body
     */
    public double[] calculate_force(Body body) {
        double delta_x = body.position[0] - this.position[0];
        double delta_y = body.position[1] - this.position[1];
        double distance = Math.sqrt(delta_x * delta_x + delta_y * delta_y) + 0.01;
        double force = (this.mass * body.mass * GCONSTANT) / (distance * distance);
        return new double[]{force * delta_x / distance, force * delta_y / distance};
    }

    /**
     * Propagates velocityes and positions given a force.
     * Uses Leapfrog integration method to approximate position.
     * @param force
     * @param delta_time
     */
    public void propagate(double[] force, double delta_time) {
        double[] acceleration = new double[2];
        acceleration[0] = force[0] / this.mass;
        acceleration[1] = force[1] / this.mass;

        this.velocity[0] += acceleration[0] * delta_time;
        this.velocity[1] += acceleration[1] * delta_time;


        this.position[0] += this.velocity[0] * delta_time;
        this.position[1] += this.velocity[1] * delta_time;
    }

    @Override
    public String toString() {
        return "Body{" +
                "position=" + Arrays.toString(position) +
                ", velocity=" + Arrays.toString(velocity) +
                '}';
    }

    // Functions that (in this case specifically) handle .json parsing
    public double getmass() {
        return mass;
    }

    public double[] getposition() {
        return position;
    }

    public double[] getvelocity() {
        return velocity;
    }

    public Color getcolour() {
        return switch (this.colour) {
            case "black" -> StdDraw.BLACK;
            case "green" -> StdDraw.GREEN;
            case "orange" -> StdDraw.ORANGE;
            case "red" -> StdDraw.RED;
            case "yellow" -> StdDraw.YELLOW;
            default -> StdDraw.BLACK;
        };
    }
}


