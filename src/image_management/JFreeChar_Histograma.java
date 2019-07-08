/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_management;

import java.util.ArrayList;
import javax.swing.JRadioButton;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.opencv.core.Mat;

/**
 *
 * @author devel
 */
public class JFreeChar_Histograma {

    public static ChartPanel createHistogram(Mat m) {
        JFreeChart histogram;
        HistogramDataset data = new HistogramDataset();
        double[] newSpec;
        ArrayList<Double> B = new ArrayList<>(), 
                G = new ArrayList<>(),
                R = new ArrayList<>();
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                newSpec = m.get(i, j);
                B.add(newSpec[0]);
                G.add(newSpec[1]);
                R.add(newSpec[2]);
            }
        }
        double[] B_arr = B.stream().mapToDouble(Double::doubleValue).toArray(),
                G_arr = G.stream().mapToDouble(Double::doubleValue).toArray(),
                R_arr = R.stream().mapToDouble(Double::doubleValue).toArray();
        data.addSeries("blue", B_arr, B_arr.length);
        data.addSeries("gree", G_arr, G_arr.length);
        data.addSeries("red", R_arr, R_arr.length);
        histogram = ChartFactory.createHistogram("image_info", "Pixeles", "Cantidad" , data, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel Panel = new ChartPanel(histogram);
        JRadioButton rb_rojo = new JRadioButton("rojo");
        JRadioButton rb_verde = new JRadioButton("verde");
        Panel.add(new JRadioButton("rojo"));
        Panel.add(new JRadioButton("verde"));
        Panel.add(new JRadioButton("azul"));
        return Panel;
    }
}
