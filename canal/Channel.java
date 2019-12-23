package canal;

public class Channel {
	int hz;
	private double[] amp, phase, graphic;
	
	public Channel (double[] amp, double[] phase, int hz, String mod) {
		this.hz = hz;
		this.amp = this.phase = this.graphic = new double[amp.length];
		
		if (mod.equals("BPSK")) {
			bpsk();
		} else {
			channelMod();
		}
			
	}
	
	private void bpsk() {
		double var = Math.PI;
		
		for (int i = 0; i < this.amp.length; i++) {
			if (i == 0) {
				var = var;
			} else {
				if (this.amp[i] != this.amp[i-1] || this.phase[i] != this.phase[i-1]) {
					var = -var;
				}
			}
			graphic(i, var);
		}
	}
	
	private void channelMod() {
		for (int i = 0; i < this.amp.length; i++) {
			graphic(i, this.phase[i]);
		}
	}

	private void graphic(int value, double phase) {
		this.graphic[value] = this.amp[value] * Math.cos(2 * Math.PI * this.hz * Math.pow(10, 6) + phase);
	}
}
