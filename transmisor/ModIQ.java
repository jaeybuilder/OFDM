package transmisor;

public class ModIQ {
	int channel;
	double[][] values;
	double[] amp, phase;
	
	public ModIQ(int channel, double[][] s) {
		this.values = s;
		this.channel = channel;
		this.amp = this.phase = new double[s.length];
		
		System.out.println("- Modulador IQ -");
		modiq();		
	}
	
	private void modiq() {
		double A; //fase
		double phi; //phi
		  
		for (int i = 0; i < this.phase.length; i++) {
			A = Math.sqrt(Math.pow(this.values[i][0], 2) + Math.pow(this.values[i][1], 2));
			phi = Math.atan(this.values[i][1] / this.values[i][0]);
			this.amp[i] = A;
			this.phase[i] = phi;
		}
	}
	
	public double[] getAmp() {
		return this.amp;
	}
			
	public double[] getFase() {
		return this.phase;
	}

}
