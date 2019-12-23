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
public class XYLineAndShapeRendererDemo extends ApplicationFrame {
    double[] values;

    public XYLineAndShapeRendererDemo(final String title, double[] s) {

        super(title);
        this.values = s;
        XYDataset dataset = createSampleDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "MHz",
                "dB",
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
        //renderer.setSeriesLinesVisible(1, false);
        //renderer.setSeriesShapesVisible(1, true);
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
        double mhz = 0;
        for (int i = 0; i < this.values.length; i++) {
            series1.add(mhz, this.values[i]);
            mhz += 0.3125;
        }
    //    series1.add(1.0, 3.3);
    //    series1.add(2.0, 4.4);
    //    series1.add(3.0, 1.7);
    //    XYSeries series2 = new XYSeries("Series 2");
    //    series2.add(1.0, 7.3);
    //    series2.add(2.0, 6.8);
    //    series2.add(3.0, 9.6);
    //    series2.add(4.0, 5.6);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
    //    dataset.addSeries(series2);
        return dataset;
    }

}