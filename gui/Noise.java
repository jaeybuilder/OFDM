package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 * A simple demonstration of the {@link XYLineAndShapeRenderer} class.
 */
public class Noise extends ApplicationFrame {
    double[] values;
    double init, increment;

    public Noise(final String title, double[] s, double init, double increment) {

        super(title);
        this.values = s;
        this.init = init;
        this.increment = increment;

        XYDataset dataset = createSampleDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Ro",
                "Magnitud",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 350));
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset.
     *
     * @return A dataset.
     */
    private XYDataset createSampleDataset() {
        XYSeries series1 = new XYSeries("Series 1");
        double Ro = this.init;
        for (int i = 0; i < 80; i++) {
            series1.add(Ro, this.values[i]);
            Ro += this.increment;
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }

}