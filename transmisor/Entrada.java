package transmisor;

import java.util.ArrayList;
import java.util.Random;

public class Entrada {
	private ArrayList<Integer> result = new ArrayList<>();
	
	public Entrada (int bits) {
		System.out.println("Entrada:");
		for (int i = 0; i < bits; i++) {
			Random random = new Random();
			this.result.add(random.nextInt(2));
		}
		System.out.println(this.result);
	}
	
	public ArrayList<Integer> getResult() {
		return this.result;
	}
	
}
