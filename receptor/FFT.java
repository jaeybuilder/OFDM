package receptor;

import java.util.ArrayList;

public class FFT {
    private int N;
    private ArrayList<Double[][]> result = new ArrayList<Double[][]>();

    public FFT(ArrayList<Double[][]> s) {
        int half, aux, var, var2, in;
        this.N = s.get(0).length;// Tamaño del arreglo.

        for (int j = 0; j < s.size(); j++) {
            half = this.N;
            aux = 0;
            var2 = 1;
            double[][] matrix = new double[this.N][2];;
            double[][] fft = new double[this.N][2];
            for (int i = 0; i < this.N; i++) {
                matrix[i][0] = s.get(j)[i][0];
                matrix[i][1] = s.get(j)[i][1];
            }
            do {
                half = half / 2;
                var = 0;
                for (int n = 0; n < this.N; n++) {
                    if (aux == half * 2)
                        aux = 0;
                    if (aux < half) {
                        in = n + half;
                    } else {
                        in = n - half;
                    }
                    aux++;
                    //System.out.println("out:" + n + " in:" + in);
                    if (n < in) {
                        fft[n][0] = matrix[n][0] + matrix[in][0];
                        fft[n][1] = matrix[n][1] + matrix[in][1];
                        var = 0;
                    } else {
                        fft[n][0] = -matrix[n][0] + matrix[in][0];
                        fft[n][1] = -matrix[n][1] + matrix[in][1];
                        double[][] complex = complex(+fft[n][0], fft[n][1], getx(var), -gety(var));
                        fft[n][0] = complex[0][0];
                        fft[n][1] = complex[0][1];
                        var += var2;
                    }
                }
                aux = 0;
                for (int i = 0; i < this.N; i++) {
                    matrix[i][0] = fft[i][0];
                    matrix[i][1] = fft[i][1];
                }
                var2 *= 2;
            } while (half != 1);

            Double[][] add = new Double[this.N][2];
            add = pour(fft);
            this.result.add(add);
        }

        //for (int j = 0; j < this.result.size(); j++) {
        //    System.out.print("Transformada directa: \n");
        //    for (int i = 0; i < this.N; i++)
        //        System.out.println(this.result.get(j)[i][0] + " i, " + this.result.get(j)[i][1] + "j");
        //}

        //Bit reversal
        for (int j = 0; j < s.size(); j++) {
            Double[][] a = new Double[this.N][2];
            for (int i = 0; i < this.N; i++) {
                a[i][0] = this.result.get(j)[i][0];
                a[i][1] = this.result.get(j)[i][1];
            }
            for (int i = 0; i < this.N; i++) {
                this.result.get(j)[i][0] = a[bitReversal(i)][0];
                this.result.get(j)[i][1] = a[bitReversal(i)][1];
            }
        }

        //for (int j = 0; j < s.size(); j++) {
        //   System.out.print("Transformada directa bitreversal: \n");
        //    for (int i = 0; i < this.N; i++)
        //        System.out.println(this.result.get(j)[i][0] + " i, " + this.result.get(j)[i][1] + "j");
        //}
    }

    private double getx(int in) { return Math.cos(2 * in * Math.PI / this.N); }

    private double gety(int in) { return Math.sin(2 * in * Math.PI / this.N); }

    private int bitReversal(int num) {
        String bitString = Integer.toBinaryString(num);
        int limit = Integer.toBinaryString(this.N - 1).length();

        for (int i = 0; i < limit; i++)
            if (bitString.length() < limit)
                bitString = "0" + bitString;

        String bitReversed = new StringBuilder(bitString).reverse().toString();
        int pos = Integer.parseInt(bitReversed, 2);
        return pos;
    }

    private double[][] complex(double a, double b, double c, double d) {
        double[][] mult = new double[1][2];
        mult[0][0] = (a*c - b*d);
        mult[0][1] = (a*d + b*c);
        return mult;
    }

    private Double[][] pour(double[][] matrix) {
        Double[][] add = new Double[matrix.length][2];
        for (int i = 0; i < matrix.length; i++) {
            add[i][0] = matrix[i][0];
            add[i][1] = matrix[i][1];
        }
        return add;
    }

    public ArrayList<Double[][]> getResult() {
        return this.result;
    }

}