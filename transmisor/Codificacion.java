package transmisor;

import java.util.ArrayList;

public class Codificacion {
	ArrayList<Integer> values;
	ArrayList<Integer> result = new ArrayList<>();

	public Codificacion (String cod, ArrayList<Integer> s) {
		this.values = s;
		switch (cod) {
			case "one":
				this.one();
				break;
			
			case "two":
				this.two();
				break;
			
			case "three":
				this.three();
				break;
		}
	}
	
	private void one() {
		int[] block = {0, 0, 0};
		int first, second;

		//Se comporta como una compuerta XOR
		for (int i = 0; i < this.values.size(); i++) {
			block[2] = block[1];
			block[1] = block[0];
			block[0] = this.values.get(i);

			if ( block[0] == (block[2]) )
				first = 0;
			else
				first = 1;

			if ( block[1] == (first) )
				second = 0;
			else
				second = 1;
			
			this.result.add(first);
			this.result.add(second);
		}
	}
	
	private void two() {
		int[] block = {0, 0, 0};
		int first, second, third;

		//Compuerta XOR
		for (int i = 0; i < this.values.size(); i++) {
			block[2] = block[1];
			block[1] = block[0];
			block[0] = this.values.get(i);

			if ( block[0] == block[2] )
				first = 0;
			else
				first = 1;

			if ( block[1] == first )
				second = 0;
			else
				second = 1;

			i++;
			if ( i < this.values.size() - 1)
				third = this.values.get(i);
			else
				third = 0;

			this.result.add(first);
			this.result.add(second);
			this.result.add(third);
		}
	}

	private void three() {
		int[] block = {0, 0, 0};
		int first, second, third, fourth;

		for (int i = 0; i < this.values.size(); i++) {
			block[2] = block[1];
			block[1] = block[0];
			block[0] = this.values.get(i);

			if ( block[0] == block[2] )
				first = 0;
			else
				first = 1;

			if ( block[1] == first )
				second = 0;
			else
				second = 1;

			i++;
			if ( i < this.values.size() - 1)
				third = this.values.get(i);
			else
				third = 0;

			i++;
			if ( i < this.values.size() - 1)
				fourth = this.values.get(i);
			else
				fourth = 0;

			this.result.add(first);
			this.result.add(second);
			this.result.add(third);
			this.result.add(fourth);
		}
	}

	public ArrayList<Integer> getResult() {
		System.out.print("Codificación: \n");
		System.out.println(this.result);
		return this.result;
	}
	
}
