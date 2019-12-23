package receptor;

import java.util.ArrayList;

public class Decodificacion {
    private ArrayList<Integer> values;
    private ArrayList<Integer> result = new ArrayList<Integer>();

    public Decodificacion(String cod, ArrayList<Integer> s) {
        this.values = s;

        System.out.print("Decodificación: \n");
        switch (cod) {
            case "one":
                this.one();
                break;

            case "two":
                //this.two();
                break;

            case "three":
                //this.three();
                break;
        }
    }

    private void one() {
        int[] block = {0, 0, 0};
        int first, second;

        //Se comporta como una compuerta XOR
        for (int i = 0; i < this.values.size(); i++) {
            block[2] = block[1];
            first = this.values.get(i);
            second = this.values.get(i+1);
            i++;

            if ( first == second )
                block[1]= 0;
            else
                block[1] = 1;

            if ( block[2] == first )
                block[0] = 0;
            else
                block[0] = 1;

            this.result.add(block[0]);
        }
    }

    public ArrayList getResult(){
        System.out.println(this.result);
        return this.result;
    }
}
