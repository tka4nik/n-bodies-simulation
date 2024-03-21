package org.example;

import javax.imageio.ImageTranscoder;
import java.util.ArrayList;
import java.util.Arrays;

public class Body {
    double mass = 1;
    double[] position = {0, 0};
    double[] velocity = {0, 0};
    public static final double GCONSTANT = 6.67 * Math.pow(10,-11);

    public Body(double x, double y, double vx, double vy, double mass) {
        this.position[0] = x;
        this.position[1] = y;
        this.velocity[0] = vx;
        this.velocity[1] = vy;
        this.mass = mass;
    }

    public double[] calculate_force(Body body) {
        double delta_x = body.position[0] - this.position[0];
        double delta_y = body.position[1] - this.position[1];
        double distance = Math.sqrt(delta_x * delta_x + delta_y * delta_y) + 0.01;
        System.out.println(distance);
        double force = (this.mass * body.mass * GCONSTANT) / (distance * distance);

        return new double[]{force * delta_x / distance, force * delta_y / distance};
    }

    public void propagate(double[] force, double delta_time) {
//        System.out.println("propagate" + Arrays.toString(force));

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
}
