package transmisor;

import java.util.ArrayList;

public class QPSK {
	private double mag;
	private double[][] result;
	
	public QPSK (double d, ArrayList<Integer> s) {
		this.mag = Math.sqrt(2*d);
		this.result = new double[s.size()/2][2];
		int aux = 0;
		
		System.out.print("QPSK: \n");
		for (int n = 0; n < s.size(); n++) {
			this.result[aux][0] = this.map(s.get(n));
			n++;
			this.result[aux][1] = this.map(s.get(n));
			aux++;
		}
		
		for (int n = 0; n < s.size()/2; n++) 
			System.out.print(this.result[n][0] + " i, " +this.result[n][1]+ " j | ");

	}
	
	private double map (int value) {
		double map = 0;
		
		switch (value) {
			case 1: 
				map = this.mag;
				break;
			
			case 0:
				map = -this.mag;
				break;
		}
		
		return map;
	}
	
	public double[][] getResult() {
		return this.result;
	}
}
	
	
