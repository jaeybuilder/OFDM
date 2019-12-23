package transmisor;

import java.util.ArrayList;

public class QAM64 {
	private double mag;
	private double[][] result;
	
	public QAM64 (double d, ArrayList<Integer> s) {
		this.result = new double[s.size()/8][2];
		this.mag = Math.sqrt(2*d);
		int aux = 0;
		String trama;
		
		System.out.print("QAM-64: \n");
		for (int n = 0; n < s.size(); n++) {
			trama = Integer.toString(s.get(n));
			trama += Integer.toString(s.get(n+1));
			trama += Integer.toString(s.get(n+2));
			trama += Integer.toString(s.get(n+3));
			this.result[aux][0] = this.map(trama);
			n += 4;
			trama = Integer.toString(s.get(n));
			trama += Integer.toString(s.get(n+1));
			trama += Integer.toString(s.get(n+2));
			trama += Integer.toString(s.get(n+3));
			this.result[aux][1] = this.map(trama);
			n += 3;
			aux++;
		}
		
		for (int n = 0; n < s.size()/8; n++) 
			System.out.print(+n+": "+this.result[n][0] + " i, " +this.result[n][1]+ " j | ");
	}
	
	private double map (String value) {
		double map = 0;
		
		switch (value) {
			case "0000": 
				map = -this.mag;
				break;
			
			case "0001":
				map = -this.mag*7/8;
				break;
				
			case "0010":
				map = -this.mag*3/4;
				break;
				
			case "0011":
				map = -this.mag*5/8;
				break;
				
			case "0100": 
				map = -this.mag/2;
				break;
			
			case "0101":
				map = -this.mag*3/8;
				break;
				
			case "0110":
				map = -this.mag/4;
				break;
				
			case "0111":
				map = -this.mag/8;
				break;
				
			case "1000": 
				map = this.mag/8;
				break;
			
			case "1001":
				map = this.mag/4;
				break;
				
			case "1010":
				map = this.mag*3/8;
				break;
				
			case "1011":
				map = this.mag/2;
				break;
				
			case "1100": 
				map = this.mag*5/8;
				break;
			
			case "1101":
				map = this.mag*3/4;
				break;
				
			case "1110":
				map = this.mag*7/8;
				break;
				
			case "1111":
				map = this.mag;
				break;
		}
		
		return map;
	}
	
	public double[][] getResult() {
		return this.result;
	}
}
