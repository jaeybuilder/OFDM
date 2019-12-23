package receptor;

import java.util.ArrayList;

public class Removepc {
    private ArrayList<Double[][]> result = new ArrayList<Double[][]>();

    public Removepc(ArrayList<Double[][]> s) {
        Double[][] value;

        for (int j = 0; j < s.size(); j++) {
            value = s.get(j);
            Double[][] trama = new Double[64][2];
            for (int i= 0; i < 64; i++) {
                trama[i][0] = value[i][0];
                trama[i][1] = value[i][1];
            }
            this.result.add(trama);
        }

        System.out.print("Remove PC: \n");
        for (int i = 0; i < this.result.size(); i++) {
            for (int l = 0; l < 64; l++) {
                System.out.print(l+": "+this.result.get(i)[l][0]+", "+this.result.get(i)[l][1]+" | ");
            }
            System.out.print("\n");
        }
    }

    public ArrayList<Double[][]> getResult() {
        return result;
    }
}
