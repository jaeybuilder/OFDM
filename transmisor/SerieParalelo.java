package transmisor;

import java.util.ArrayList;

public class SerieParalelo {
	private Double[][] serie;
	private ArrayList<Double[][]> result = new ArrayList<Double[][]>();

	public SerieParalelo(double[][] s) {
		this.serie = new Double[s.length][2];

		System.out.print("\n Serie a Paralelo transmisor: \n");
		for (int i = 0; i < s.length; i++) {
			this.serie[i][0] = s[i][0];
			this.serie[i][1] = s[i][1];
		}
		result.add(this.serie);

		for (int i = 0; i < s.length; i++)
			System.out.print(i+": "+this.serie[i][0] +" i, "+this.serie[i][1]+"j | ");
	}
	
	public ArrayList<Double[][]> getResult() {
		return this.result;
	}
	
}



