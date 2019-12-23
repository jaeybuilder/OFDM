package receptor;

import java.util.ArrayList;

public class SerieparReceptor {
    private ArrayList<Double[][]> result = new ArrayList<Double[][]>();

    public SerieparReceptor(double[][] s) {
        int k = 0;
        int size = s.length/80;

        System.out.print("\n Serie a Paralelo receptor: \n");
        for (int i = 0; i < size; i++) {
            Double[][] aux2 = new Double[80][2];
            for (int j = 0; j < 80; j++) {
                aux2[j][0] = s[k][0];
                aux2[j][1] = s[k][1];
                k++;
            }
            this.result.add(aux2);
        }

        for (int i = 0; i < this.result.size(); i++) {
            for (int l = 0; l < 80; l++) {
                System.out.print(l+": "+this.result.get(i)[l][0]+", "+this.result.get(i)[l][1]+" | ");
            }
            System.out.print("\n");
        }
    }

    public ArrayList<Double[][]> getResult() {
        return this.result;
    }

}



