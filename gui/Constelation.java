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

public class Constelation extends ApplicationFrame {
    double[][] values;

    public Constelation(final String title, double[][] s) {

        super(title);
        this.values = s;
        XYDataset dataset = createSampleDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Q",
                "I",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 350));
        setContentPane(chartPanel);

    }

    private XYDataset createSampleDataset() {
        XYSeries series1 = new XYSeries("Series 1");
        for (int i = 0; i < this.values.length; i++) {
            series1.add(this.values[i][0], this.values[i][1]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }

}