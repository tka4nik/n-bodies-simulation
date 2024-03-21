package org.example;

import java.util.ArrayList;
import edu.princeton.cs.introcs.StdDraw;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Simulation {
    double total_time, delta_time;
    String filename;
    ArrayList<Body> bodies = new ArrayList<Body>();
    public Simulation(double total_time, double delta_time, String filename) {
        this.total_time = total_time;
        this.delta_time = delta_time;
        this.filename = filename;

        //TODO: From file in a loop
        bodies.add(new Body(-0.4, 1, 0, 0, 400000000));
        bodies.add(new Body(0.4, 1, 0, -0.15, 100000000));
        StdDraw.enableDoubleBuffering();
    }

    private void InitializeBodies(String filename) {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse

    }

    public void run() {
        System.out.println("Running");
        for (double t = 0; t < this.total_time; t+=this.delta_time) {
            do_step();
            draw();
        }
    }

    private void do_step() {
        for (int i = 0; i < bodies.size(); i++) {
            double[] net_force = new double[2];
            for (int j = 0; j < bodies.size(); j++) {
                if (j == i) continue;
                double[] i_force = bodies.get(i).calculate_force(bodies.get(j));
                net_force[0] += i_force[0];
                net_force[1] += i_force[1];
            }
            bodies.get(i).propagate(net_force, delta_time);
        }

//        System.out.println(bodies);
    }

    private void draw(){
        StdDraw.clear();
        StdDraw.setScale(-2, 2);
        for (Body body: bodies) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(body.position[0], body.position[1], 0.05);
        }
        StdDraw.show();
        StdDraw.pause(25);
    }
}
