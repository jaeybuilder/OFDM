package receptor;

import java.util.ArrayList;

public class DemapQPSK {
    private double mag;
    private ArrayList result = new ArrayList();

    public DemapQPSK(double d, double[][] s) {
        this.mag = Math.sqrt(2 * d);

        System.out.print("\nDemap QSPK: \n");

        for (int n = 0; n < s.length; n++) {
            this.result.add(this.demap(this.aprox(s[n][0])));
            this.result.add(this.demap(this.aprox(s[n][1])));
        }
    }

    private int demap(double in) {
        int demap = 0;

        if (in == 1)
            demap = 1;

        return demap;
    }

    private double aprox(double in) {
        double value;

        if (in > 0) {
            value = this.mag;
        } else {
            value = -this.mag;
        }

        return value;
    }

    public ArrayList getResult() {
        System.out.println(this.result);
        return result;
    }
}
