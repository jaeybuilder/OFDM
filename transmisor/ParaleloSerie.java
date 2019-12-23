package transmisor;

import java.util.ArrayList;

public class ParaleloSerie {
	private double[][] result;
	
	public ParaleloSerie(ArrayList<Double[][]> s) {
		int size = 0;
		for (int i = 0; i < s.size(); i++) {
			size += s.get(i).length;
		}
		this.result = new double[size][2];
		int aux = 0;

		System.out.print("Paralelo Serie: \n");

		for (int i = 0; i < s.size(); i++) {
			for (int j = 0; j < s.get(i).length; j++) {
				this.result[aux][0] = s.get(i)[j][0];
				this.result[aux][1] = s.get(i)[j][1];
				aux++;
			}
		}
		
		for (int i = 0; i < this.result.length; i++)
			System.out.print(i+": "+this.result[i][0]+ " i, " +this.result[i][1]+ "j | ");
	}
	
	public double[][] getResult() {
		return this.result;
	}
	
 }

