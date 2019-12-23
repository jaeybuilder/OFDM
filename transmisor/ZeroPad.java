package transmisor;

import java.util.ArrayList;

public class ZeroPad {
	int remove;
	ArrayList<Double[][]> result = new ArrayList<Double[][]>();
	
	public ZeroPad (ArrayList<Double[][]> s) {
		int aux, j, k, m; 
		Double[][] value= s.get(0); 
		aux = value.length;
		j = 48;
		k = m = 0;

		do {
			Double[][] trama = new Double[64][2];
			if (aux < j) {
				j = aux;
			}
			for (int i= 0; i < j; i++) {
				trama[i][0] = value[k][0];
				trama[i][1] = value[k][1];
				k++;
				aux--;
			}
			for (int i = j; i < 64; i++) {
				trama[i][0] = (double) 0;
				trama[i][1] = (double) 0;
			}
			this.result.add(m, trama);
			m++;
		} while (aux > 0);
		this.remove = j;

		System.out.print("\n Padding of Zero added: \n");
		for (int i = 0; i < this.result.size(); i++) {
			for (int l = 0; l < 64; l++) {
				System.out.print(l+": "+this.result.get(i)[l][0]+", "+this.result.get(i)[l][1]+" | ");
			}
			System.out.print("\n");
		}
	}
	
	public ArrayList<Double[][]> getResult () {
		return this.result;
	}

	public int getRemove() {
		return this.remove;
	}
}
