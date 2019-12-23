package receptor;

import java.util.ArrayList;

public class Viterbi {
    private ArrayList<Integer> values;
    private ArrayList<Integer> result = new ArrayList<>();

    public Viterbi(String decod, ArrayList<Integer> s) {
        this.values = s;
        switch (decod) {
            case "one":
                this.one();
                break;

            case "two":
                this.two();
                break;

            case "three":
                this.three();
                break;
        }
    }

    private void one(){
        Double math = Math.pow(2,this.values.size()/2);
        int size = math.intValue();
        int aux1 = 1, aux2 = 0, aux3 = 0, aux4 = size, aux5 = 0;

        //Distancia
        int[] d = new int[size];
        int d1, d2;

        //Cadena
        String c1, c2;
        String[] chain = new String[size];
        for (int i = 0; i < size; i++) {
            chain[i] = "";
        }

        //Variables para el calculo de los estados siguientes
        String[] next = new String[size];
        String[] old = new String[size];
        String actual;
        old[0] = "00";

        for (int i = 0; i < this.values.size()/2 ; i++) {
            actual = this.values.get(aux3).toString() + this.values.get(aux3+1).toString();
            aux3 += 2;

            for (int j = 0; j < aux1; j++) {
                String[][] current = this.state1(old[j]);
                next[aux2] = current[0][0];
                next[aux2+1] = current[1][0];

                c1 = current[0][1];
                c2 = current[1][1];

                d1 = this.compare1(current[0][2], actual);
                d2 = this.compare1(current[1][2], actual);

                for (int k = 0; k < aux4; k++) {
                    if (k < aux4/2 ) {
                        d[aux5] += d1;
                        chain[aux5] += c1;
                    } else {
                        d[aux5] += d2;
                        chain[aux5] += c2;
                    }
                    aux5++;
                }
                aux2 += 2;
            }
            aux1 *= 2;
            aux4 /= 2;
            aux2 = aux5 = 0;
            old = next;
        }

        int index = 0, var = d[0];
        for (int i = 0; i < size ; i++) {
            if (d[i] < var) {
                index = i;
                var = d[i];
            }
        }
        System.out.println("cadena decodificada: \n" +chain[index]);
    }

    private void one1(){
        int aux1 = 0;
        int d1, d2;
        int size = this.values.size()/2;

        String actual, c1 = "", c2 = "", chain = "";
        String old = "00";
        String[] next = new String[2];
        String[][] current;

        for (int i = 0; i < size; i++) {
            actual = this.values.get(aux1).toString() + this.values.get(aux1+1).toString();
            System.out.print(actual+ " ");
            current = this.state1(old);

            next[0] = current[0][0];
            next[1] = current[1][0];

            c1 = current[0][1];
            c2 = current[1][1];

            d1 = this.compare1(current[0][2], actual);
            d2 = this.compare1(current[1][2], actual);

            if (d1 < d2) {
                old = next[0];
                chain += c1;
            }
            else {
                old = next[1];
                chain += c2;
            }
            aux1 += 2;
        }

        System.out.print("Cadena decodificada: "+chain);
    }

    private void two(){}

    private void three(){}

    private String[][] state1(String s) {
        String[][] next = new String[2][3];

        switch (s) {
            case "00":
                next[0][0] = "00";//Estado siguiente
                next[0][1] = "0";
                next[0][2] = "00";
                next[1][0] = "10";//Estado siguiente
                next[1][1] = "1";
                next[1][2] = "11";
                break;
            case "01":
                next[0][0] = "00";//Estado siguiente
                next[0][1] = "0";
                next[0][2] = "11";
                next[1][0] = "10";//Estado siguiente
                next[1][1] = "1";
                next[1][2] = "00";
                break;
            case "10":
                next[0][0] = "01";//Estado siguiente
                next[0][1] = "0";
                next[0][2] = "01";
                next[1][0] = "11";//Estado siguiente
                next[1][1] = "1";
                next[1][2] = "10";
                break;
            case "11":
                next[0][0] = "01";//Estado siguiente
                next[0][1] = "0";
                next[0][2] = "10";
                next[1][0] = "11";//Estado siguiente
                next[1][1] = "1";
                next[1][2] = "01";
                break;
        }
        return next;
    }

    private int compare1 (String out, String s) {
        int val = 2;
        if (out.substring(0,1).equals(s.substring(0,1)))
            val--;
        if (out.substring(1,2).equals(s.substring(1,2)))
            val--;
        return val;
    }

    private int compare2 (String out, String s) {
        int val = 3;
        if (out.substring(0,1).equals(s.substring(0,1)))
            val--;
        if (out.substring(1,2).equals(s.substring(1,2)))
            val--;
        return val;
    }

    private int compare3 (String out, String s) {
        int val = 4;
        if (out.substring(0,1).equals(s.substring(0,1)))
            val--;
        if (out.substring(1,2).equals(s.substring(1,2)))
            val--;
        return val;
    }

    public ArrayList<Integer> getResult() {
        return this.result;
    }
}
