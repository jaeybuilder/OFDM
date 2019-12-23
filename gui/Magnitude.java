package gui;

import java.util.ArrayList;

public class Magnitude {
    double[] result;
    double[][] values;

    public Magnitude ( ArrayList<Double[][]> s) {
        int size = 0, aux = 0;
        for (int i = 0; i < s.size(); i++) {
            size += s.get(i).length;
        }
        this.result = new double[size];
        this.values = new double[size][2];

        for (int i = 0; i < s.size(); i++) {
            for (int j = 0; j < s.get(i).length ; j++) {
                this.values[aux][0] = s.get(i)[j][0];
                this.values[aux][1] = s.get(i)[j][1];
                aux++;
            }
        }

        for (int i = 0; i < size ; i++) {
            this.result[i] = mag(this.values[i][0], this.values[i][1]);
        }
    }

    private double mag (double in1, double in2) {
        double value = 10 * Math.log( Math.sqrt(Math.pow(in1, 2) + Math.pow(in2, 2)) /0.001 );
        return value;
    }

    public double[] getResult() {
        return result;
    }
}
