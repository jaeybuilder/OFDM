package transmisor;

import java.util.ArrayList;

public class QAM16 {
	private double mag;
	private double[][] result;
	
	public QAM16 (double d, ArrayList<Integer> s) {
		this.result = new double[s.size()/4][2];
		this.mag = Math.sqrt(2*d);
		int aux = 0;
		String trama;
		
		System.out.print("QAM-16: \n");
		for (int n = 0; n < s.size(); n++) {
			trama = Integer.toString(s.get(n));
			trama += Integer.toString(s.get(n+1));
			this.result[aux][0] = this.map(trama);
			n += 2;
			trama = Integer.toString(s.get(n));
			trama += Integer.toString(s.get(n+1));
			this.result[aux][1] = this.map(trama);
			n++;
			aux++;
		}
		
		for (int n = 0; n < s.size()/4; n++) 
			System.out.print(this.result[n][0] + " i, " +this.result[n][1]+ " j | ");
	}
	
	private double map (String value) {
		double map = 0;
		
		switch (value) {
			case "00": 
				map = -this.mag;
				break;
			
			case "01":
				map = -this.mag/2;
				break;
				
			case "10":
				map = this.mag/2;
				break;
				
			case "11":
				map = this.mag;
				break;
		}
		
		return map;
	}
	
	public double[][] getResult() {
		return this.result;
	}
}
