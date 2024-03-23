package org.example;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.princeton.cs.introcs.StdDraw;

/**
 * Class that handles N-body simulation.
 * Constructor accepts a .json config filename, which contains description of a system
 * Uses StdDraw library for visualisation.
 */
public class Simulation {

    double delta_time;
    long radius;
    String filename;
    ArrayList<Body> bodies = new ArrayList<>();
    public Simulation(String filename) {
        this.filename = filename;
        this.InitializeBodies(filename);

        StdDraw.setCanvasSize(900, 900);
        StdDraw.enableDoubleBuffering();
    }

    /**
     * Parses .json config file
     * @param filename config file.
     */
    private void InitializeBodies(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Config config = mapper.readValue(new File(filename), Config.class);
            this.delta_time = config.getDelta_time();
            this.radius = config.getRadius();
            this.bodies = config.getBodies();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Main loop. Runs until user presses SPACE .
     */
    public void run() {
        System.out.println("Running");
        while (!StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            do_step();
            draw();
        }
        System.exit(1);
    }

    /**
     * Computes forces for one frame
     * Loops through all bodies and calculate net sum of forces for each of the bodies,
     * then calls propagate method for each body to update their positions.
     */
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
    }

    /**
     * Draws bodies. Uses StdDraw library.
     */
    private void draw(){
        StdDraw.setScale(-this.radius, this.radius);
        for (Body body: bodies) {
            StdDraw.setPenColor(body.getcolour());
            StdDraw.filledCircle(body.position[0], body.position[1], 1000000000);
        }
        StdDraw.show();
        StdDraw.pause(10);
    }
}
