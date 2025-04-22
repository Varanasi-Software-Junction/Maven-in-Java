/*
 * This is a basic Java Swing application that uses the JFreeChart library
 * to draw a scatter plot with a line connecting the points.
 */
package graphscharts;  // The package name for organizing this class file

// Import necessary JFreeChart and Swing classes
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class CharApp extends JFrame {  // The class extends JFrame to create a window for displaying the chart

    // Constructor for the CharApp class
    public CharApp() {
        // Set window title
        setTitle("Scatter Plot Example");

        // Set initial window size (width x height)
        setSize(800, 600);

        // Define what happens when user closes the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ----------------- Step 1: Create the Dataset -----------------
        // Create a collection that can hold one or more series of XY data
        XYSeriesCollection dataset = new XYSeriesCollection();

        // Create a series of (x, y) data points
        XYSeries series1 = new XYSeries("Scatter Points");

        // Add data points to the series — these will appear as dots and be connected with lines
        series1.add(10, 20);
        series1.add(50, 70);
        series1.add(100, 120);
        series1.add(150, 50);
        series1.add(200, 200);
        series1.add(250, 250);
        series1.add(300, 180);
        series1.add(350, 300);
        series1.add(400, 350);
        series1.add(450, 400);

        // Add this series to the dataset collection
        dataset.addSeries(series1);

        // ----------------- Step 2: Create the Chart -----------------
        // Use ChartFactory to quickly create a scatter plot
        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                "Scatter Plot with Line", // Chart title
                "X-Axis", // Label for X-axis
                "Y-Axis", // Label for Y-axis
                dataset, // The dataset created above
                PlotOrientation.VERTICAL, // Orientation: vertical (Y increases upward)
                true, // Show legend
                true, // Use tooltips
                false // Don't use URLs
        );

        // ----------------- Step 3: Customize the Plot -----------------
        // Extract the plot object from the chart for further customization
        XYPlot plot = scatterPlot.getXYPlot();

        // Create a renderer to control how the data points are drawn (shapes, lines, etc.)
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        // Enable line drawing between points in series 0 (our only series here)
        renderer.setSeriesLinesVisible(0, true);

        // Enable shape markers (dots) for each point in the series
        renderer.setSeriesShapesVisible(0, true);

        // Apply this renderer to the plot
        plot.setRenderer(renderer);

        // Set the plot background color to white (default is gray)
        plot.setBackgroundPaint(Color.white);

        // ----------------- Step 4: Embed Chart into Window -----------------
        // Wrap the chart inside a ChartPanel (a Swing-compatible component)
        ChartPanel chartPanel = new ChartPanel(scatterPlot);

        // Set the chart panel as the content pane of the JFrame
        setContentPane(chartPanel);
    }

    // ----------------- Main Method: Launch the Application -----------------
    public static void main(String[] args) {
        // Ensure that GUI updates happen on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create an instance of CharApp (which sets up the chart)
            CharApp example = new CharApp();

            // Make the application window visible
            example.setVisible(true);
        });
    }
}
