package receptor;

import java.util.ArrayList;

public class DemapBPSK {
    private ArrayList<Integer> result = new ArrayList();

    public DemapBPSK(double[][] s) {
        System.out.print("\nDemap BPSK: \n");
        for (int i = 0; i <s.length ; i++)
            if (s[i][0] >= 0.0) {
                this.result.add(1);
            } else {
                this.result.add(0);
            }
    }

    public ArrayList<Integer> getResult() {
        System.out.println(this.result);
        return this.result;
    }
}
