package receptor;

import java.util.ArrayList;

public class DemapQAM64 {
    private double QPSK;
    private ArrayList<Integer> result = new ArrayList();

    public DemapQAM64(double d, double[][] s){
        this.QPSK = Math.sqrt(2 * d);
        String trama = new String();

        System.out.print("\nDemap QAM64: \n");
        for (int n = 0; n < s.length; n++) {
            trama = this.demap(s[n][0]);
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(0))));
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(1))));
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(2))));
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(3))));

            trama = this.demap(s[n][1]);
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(0))));
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(1))));
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(2))));
            this.result.add( Integer.parseInt(String.valueOf(trama.charAt(3))));
        }
    }

    private String demap(double in) {
        String value;

        if (in >= this.QPSK*15/16)
            value = "1111";
        else if (in >= this.QPSK*13/16)
            value = "1110";
        else if (in >= this.QPSK*11/16)
            value = "1101";
        else if (in >= this.QPSK*9/16)
            value = "1100";
        else if (in >= this.QPSK*7/16)
            value = "1011";
        else if (in >= this.QPSK*5/16)
            value = "1010";
        else if (in >= this.QPSK*3/16)
            value = "1001";
        else if (in >= 0)
            value = "1000";
        else if (in >= -this.QPSK*3/16)
            value = "0111";
        else if (in >= -this.QPSK*5/16)
            value = "0110";
        else if (in >= -this.QPSK*7/16)
            value = "0101";
        else if (in >= -this.QPSK*9/16)
            value = "0100";
        else if (in >= -this.QPSK*11/16)
            value = "0011";
        else if (in >= -this.QPSK*13/16)
            value = "0010";
        else if (in >= -this.QPSK*15/16)
            value = "0001";
        else
            value = "0000";

        return value;
    }

    public ArrayList getResult() {
        System.out.println(this.result);
        return result;
    }


}
