package receptor;

import java.util.ArrayList;

public class Removepad {
    private ArrayList<Double[][]> result = new ArrayList<>();

    public Removepad(ArrayList<Double[][]> s, int remove) {
        int size = s.size();
        int aux1 = 0;
        int aux2;
        Double[][] value;

        for (int n = 0; n < size; n++) {
            value = s.get(n);
            aux1++;
            if (aux1 != size)
                aux2 = 48;
            else
                aux2 = remove;
            Double[][] trama = new Double[aux2][2];
            for (int i = 0; i < aux2; i++) {
                trama[i][0] = value[i][0];
                trama[i][1] = value[i][1];
            }
            this.result.add(trama);
        }

        System.out.print("Remove pad: \n");
        aux1 = 0;
        for (int i = 0; i < this.result.size(); i++) {
            for (int l = 0; l < this.result.get(i).length; l++) {
                System.out.print(l+": "+this.result.get(i)[l][0]+", "+this.result.get(i)[l][1]+" | ");
            }
            System.out.print("\n");
        }
    }

    public ArrayList<Double[][]> getResult() {
        return this.result;
    }
}
