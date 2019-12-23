package transmisor;

import java.util.ArrayList;

public class BPSK {
	double[][] result;
	
	public BPSK(double d, ArrayList<Integer> s) {
		this.result = new double [s.size()][2];

		System.out.print("BPSK: \n");
		for (int n = 0; n < s.size(); n++) {
			double BPSK = Math.sqrt(2*d) * Math.cos(Math.PI * (s.get(n)-1));
			this.result[n][0] = BPSK;
			System.out.print(this.result[n][0]+", ");
		}
	}
	
	public double[][] getResult() {
		return this.result;
	}
	
}
