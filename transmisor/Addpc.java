package transmisor;

import java.util.ArrayList;

public class Addpc {
	private ArrayList<Double[][]> result = new ArrayList<Double[][]>();
	
	public Addpc (ArrayList<Double[][]> s) {
		
		for (int i = 0; i < s.size(); i++) {
			Double[][] aux = new Double[80][2];
			for (int j = 0; j < 64; j++) {
				aux[j][0] = s.get(i)[j][0];
				aux[j][1] = s.get(i)[j][1];
			}
			int k = 0;
			for (int j = 64; j < 80; j++) {
				aux[j][0] = s.get(i)[k][0];
				aux[j][1] = s.get(i)[k][1];
				k++;
			}
			this.result.add(aux);
		}

		System.out.println("Added PC to: " +s.size());
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
