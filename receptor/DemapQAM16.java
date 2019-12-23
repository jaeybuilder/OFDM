package receptor;

import java.util.ArrayList;

public class DemapQAM16 {
    private double QPSK;
    private ArrayList<Integer> result = new ArrayList();

    public DemapQAM16(double d, double[][] s){
        this.QPSK = Math.sqrt(2 * d);
        String trama = "";

        System.out.print("\nDemap QAM16: \n");
        for (int n = 0; n < s.length; n++) {
            trama = this.demap(s[n][0]);
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(0))));
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(1))));

            trama = this.demap(s[n][1]);
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(0))));
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(1))));
        }
    }

    private String demap(double in) {
        String value;

        if (in >= this.QPSK*3/4)
            value = "11";
        else if (in >= 0)
            value = "10";
        else if (in > -this.QPSK*3/4)
            value = "01";
        else
            value = "00";

        return value;
    }

    public ArrayList getResult() {
        System.out.println(this.result);
        return result;
    }
}
