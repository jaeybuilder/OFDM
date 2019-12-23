package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

import canal.AWGN;
import org.jfree.ui.RefineryUtilities;
import receptor.*;
import transmisor.*;

public class Visual extends JFrame implements ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;
	ArrayList<String> value = new ArrayList<String>();
	private JLabel label1, label2, label3, label4, label5, label6;
	private JComboBox<String> combo1, combo2, combo3, combo5;
	private JComboBox<Integer> combo4;
	private JButton boton1;

	public Visual() {
		ActionListener act;
		act = arg0 -> {
			// TODO Auto-generated method stub
			String value = combo1.getSelectedItem().toString();

			if (value == "IEEE 802.11 n") {
				boton1.setEnabled(false);
			}
			else {
				boton1.setEnabled(true);
			}
			if (value == "IEEE 802.11 a") {
				combo4.removeAllItems();
				comboOp1();
			} else if (value == "IEEE 802.11 g") {
				combo4.removeAllItems();
				comboOp3();
			}
		};

		setLayout(null);
        label1();
        label2();
        combo1();
        label3();
        combo2();
        label4();
        combo3();
        label5();
        combo4();
        label6();
        combo5();
        combo1.addActionListener(act);
        btn();
    }

//    public static void main(String[] args) {
//    	window();
//    }
    
    private void label1() {
    	label1 = new JLabel("SIMULADOR OFDM PARA LOS ESTANDARES IEEE 802.11 a/g/n");
    	label1.setBounds(20,20,400,30);
    	add(label1);
    }
    
    private void label2() {
    	label2 = new JLabel("Estandar:");
    	label2.setBounds(20,60,100,30);
    	add(label2);
    }
    
    private void label3() {
    	label3 = new JLabel("Modulación:");
    	label3.setBounds(20,120,100,30);
    	add(label3);
    }
    
    private void label4() {
    	label4 = new JLabel("Ruido AWGN:");
    	label4.setBounds(20,180,100,30);
    	add(label4);
    }
    
    private void label5() {
    	label5 = new JLabel("Frecuencia:");
    	label5.setBounds(220,60,100,30);
    	add(label5);
    }

	private void label6() {
		label6 = new JLabel("Mostrar gráficas:");
		label6.setBounds(220,120,140,30);
		add(label6);
	}
    
    private void combo1() {
    	 combo1 = new JComboBox<String>();
         combo1.setBounds(20,90,180,25);
         add(combo1);
         combo1.addItem("IEEE 802.11 a");
         combo1.addItem("IEEE 802.11 g");
         combo1.addItem("IEEE 802.11 n");
         combo1.addItemListener(this);
    }
    
    private void combo2() {
   	 	combo2 = new JComboBox<String>();
        combo2.setBounds(20,150,180,25);
        add(combo2);
        combo2.addItem("BPSK");
        combo2.addItem("QPSK");
        combo2.addItem("QAM-16");
        combo2.addItem("QAM-64");
        combo2.addItemListener(this);
   }
    
    private void combo3() {
   	 	combo3 = new JComboBox<String>();
        combo3.setBounds(20,210,180,25);
        add(combo3);
        combo3.addItem("Si");
        combo3.addItem("No");
        combo3.addItemListener(this);
    }
    
    private void combo4() {
   	 	combo4 = new JComboBox<Integer>();
        combo4.setBounds(220,90,180,25);
        add(combo4);
		comboOp1();
        combo4.addItemListener(this);
    }

	private void combo5() {
		combo5 = new JComboBox<String>();
		combo5.setBounds(220,150,180,25);
		add(combo5);
		combo5.addItem("No");
		combo5.addItem("Si");
		combo5.addItemListener(this);
	}
    
    private void btn() {
    	this.boton1 = new JButton("Iniciar");
    	this.boton1.setBounds(160,265,100,30);
    	add(this.boton1);
    	this.boton1.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==boton1) {
    		int number = 20; //Numero de datos a entrar
    		double d = 0.5; //Algo de la amplitud

			String value = combo1.getSelectedItem().toString();
    		String cod = "one"; //numero de codificacion
    		String type = combo2.getSelectedItem().toString(); //Tipo de mapping utilizado
    		String noise = combo3.getSelectedItem().toString(); //Agregar ruido
    		int channel = (int) combo4.getSelectedItem(); //Canal a utilizar
			String show = combo5.getSelectedItem().toString();

			//Variables para grafica del ruido
			double init = 0.0, increment = 0.0;
			double[] noiseGraphic = new double[80];

			//TRANSMISOR
    		ArrayList<Integer> entry = new Entrada(number).getResult();
    		ArrayList<Integer> codification = new Codificacion(cod, entry).getResult();
    		double[][] map = new double[entry.size()][2];
    		switch (type) {
    			case "BPSK":
    				map = new BPSK(d, codification).getResult();
    				break;
    				
    			case "QPSK":
    				map = new QPSK(d, codification).getResult();
    				break;
    				
    			case "QAM-16":
    				map = new QAM16(d, codification).getResult();
    				break;
    				
    			case "QAM-64":
    				map = new QAM64(d, codification).getResult();
    				break;
    		}
    		ArrayList<Double[][]> ser_par = new SerieParalelo(map).getResult();
    		ZeroPad pad = new ZeroPad(ser_par);
    		ArrayList<Double[][]> zeropad = pad.getResult();
    		ArrayList<Double[][]> ifft = new IFFT(zeropad).getResult();
    		ArrayList<Double[][]> pc = new Addpc(ifft).getResult();
			double[][] par_ser = new ParaleloSerie(pc).getResult();

    		//CANAL
			if (noise.equals("Si")) {
				System.out.println("esta entrando aqui");
				double[][] aux = par_ser;
				AWGN awgn = new AWGN(channel, par_ser);
				double[][] canal = awgn.getResult();
				par_ser = canal;
				noiseGraphic = awgn.getAwgn();
				init = awgn.getInit();
				increment = awgn.getIncrement();
			}

			//RECEPTOR
			System.out.print("\n RECEPTOR");
			ArrayList<Double[][]> ser_par2 = new SerieparReceptor(par_ser).getResult();
			ArrayList<Double[][]> removepc = new Removepc(ser_par2).getResult();
			ArrayList<Double[][]> fft = new FFT(removepc).getResult();
			ArrayList<Double[][]> removepad = new Removepad(fft, pad.getRemove()).getResult();
			double[][] par_ser2 = new ParaleloSerie(removepad).getResult();
			ArrayList<Integer> demap = new ArrayList<>();
			switch (type) {
				case "BPSK":
					demap = new DemapBPSK(par_ser2).getResult();
					break;

				case "QPSK":
					demap = new DemapQPSK(d, par_ser2).getResult();
					break;

				case "QAM-16":
					demap = new DemapQAM16(d, par_ser2).getResult();
					break;

				case "QAM-64":
					demap = new DemapQAM64(d, par_ser2).getResult();
					break;
			}
			ArrayList<Integer> viterbi = new Viterbi("one", demap).getResult();

			//Graficas
			XYLineAndShapeRendererDemo demo = new XYLineAndShapeRendererDemo("Espectro de señal OFDM - Transmisor", new Magnitude(pc).getResult());
			XYLineAndShapeRendererDemo demo2 = new XYLineAndShapeRendererDemo("Espectro de señal OFDM - Receptor", new Magnitude(ser_par2).getResult());
			demo.pack();
			demo.setVisible(true);
			demo2.pack();
			demo2.setVisible(true);


			if (show.equals("Si")) {
				Constelation constelation = new Constelation("Mapa de constelación - Transmisor", map);
				Constelation constelation2 = new Constelation("Mapa de constelación - Receptor", par_ser2);
				Noise n = new Noise("AWGN", noiseGraphic, init, increment);
				constelation.pack();
				constelation.setVisible(true);
				constelation2.pack();
				constelation2.setVisible(true);
				n.pack();
				n.setVisible(true);
			}
        }
    }
    
    public void window() {
    	Visual visual = new Visual();
    	visual.setBounds(10,20,420,350);
    	visual.setResizable(false);
    	visual.setVisible(true);
    }

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	private void comboOp1() {
		combo4.addItem(5150);
		combo4.addItem(5180);
		combo4.addItem(5200);
		combo4.addItem(5220);
		combo4.addItem(5240);
		combo4.addItem(5260);
		combo4.addItem(5280);
		combo4.addItem(5300);
		combo4.addItem(5320);
		combo4.addItem(5350);
		combo4.addItem(5725);
		combo4.addItem(5745);
		combo4.addItem(5765);
		combo4.addItem(5785);
		combo4.addItem(5805);
		combo4.addItem(5825);
	}
	
	private void comboOp3() {
		combo4.addItem(2412);
		combo4.addItem(2417);
		combo4.addItem(2422);
		combo4.addItem(2427);
		combo4.addItem(2432);
		combo4.addItem(2437);
		combo4.addItem(2442);
		combo4.addItem(2447);
		combo4.addItem(2452);
		combo4.addItem(2457);
		combo4.addItem(2467);
		combo4.addItem(2472);
		combo4.addItem(2487);
	}
}
