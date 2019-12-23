package canal;

public class AWGN {
    int band;
    double init, current, increment, RO;
    double[] awgn;
    double[][] result;
    double[][] values;

    public AWGN(int b, double[][] s) {
        this.band = b;
        this.values = s;
        this.awgn = new double[s.length];
        this.result = new double[s.length][2];
        this.RO = this.ro();
        double size = 8 * this.RO;
        this.init = size * -1 / 2;
        this.increment = size / s.length;
        this.current = this.init;

        System.out.print(this.init+ " RO: "+this.RO+ "increment: " +this.increment);
        System.out.print("Ruido: \n");
        this.one();
    }

    private void one(){
        for (int i = 0; i < this.values.length; i++) {
            this.result[i][0] = this.values[i][0] + awgn();
            this.result[i][1] = this.values[i][1] + awgn();
            this.awgn[i] = awgn();
            this.current += this.increment;
            System.out.print(" awgn: "+this.awgn[i] + "\n");
        }
    }

    private double awgn() {
        double awgn = (1 / (this.RO * Math.sqrt(2*Math.PI))) * Math.pow(Math.E, this.exp());
        return awgn;
    }

    private double exp() {
        double exp = (Math.pow(this.current, 2)) * -1 / (2 * Math.pow(this.RO,2));
        System.out.print(" pos: " + this.current + ", exp: " + exp);
        return exp;
    }

    private double ro() {
        double ro;
        double B = 0;
        if (this.band == 5150 || this.band == 5180 || this.band == 5200 || this.band == 5220 || this.band == 5240 || this.band == 5260 || this.band == 5280 || this.band == 5300 || this.band == 5320 || this.band == 5350) {
            B = 30000000;
        } else if (this.band == 5725 || this.band == 5745 || this.band == 5765 || this.band == 5785 || this.band == 5805 || this.band == 5825) {
            B = 20000000;
        } else {
            B = 22000000;
        }
        final double K = 1.38064852E-23; //Constante
        final double T = 303.15; //Temperatura 30 celsius
        ro = K * T * B / 2;
        //System.out.println("ro: "+ro+ ", B: "+B);
        return 2;
    }

    public double[] getAwgn() {
        return awgn;
    }

    public double getInit() {
        return init;
    }

    public double getIncrement() {
        return increment;
    }

    public double[][] getResult() {
        return this.result;
    }
}


