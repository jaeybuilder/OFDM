package transmisor;

public class Blackman {
	private int N;
	private final double a0 = 0.42;
	private final double a1 = 0.5;
	private final double a2 = 0.8;
	private double[][] result;
	
	public Blackman(double s[][]) {
		this.N = s.length;
		this.result = new double[this.N][2];
		
		System.out.println("Blackman");
		for (int i = 0; i < this.N; i++) {
			this.result[i][0] = this.a0 - this.a1 * Math.cos(2*Math.PI*s[i][0]/this.N-1) + a2 * Math.cos(4*Math.PI*s[i][0]/this.N-1);
			this.result[i][1] = this.a0 - this.a1 * Math.cos(2*Math.PI*s[i][1]/this.N-1) + a2 * Math.cos(4*Math.PI*s[i][1]/this.N-1);
			System.out.println(this.result[i][0] + " i, " +this.result[i][1]+ " j");
		}
	}
	
	public double[][] getResult () {
		return this.result;	
	}
	
}
