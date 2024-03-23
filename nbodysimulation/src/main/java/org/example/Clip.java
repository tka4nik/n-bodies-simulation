package org.example;

public class Clip {
    /**
     * Clips given vector based on its norm and given maximum norm value (unused)
     * @param vector
     * @param max
     * @return Same vector if norm < max, vector * max / norm if norm ></>= max.
     */
    public static double[] clip(double[] vector, double max) {
        double norm = Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1]);
        if (norm >= max) {
            return new double[]{vector[0] * max / norm, vector[1] * max / norm};
        }
        return vector;
    }
}
