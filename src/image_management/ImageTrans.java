/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_management;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.abs;
import org.opencv.core.Mat;

/**
 *
 * @author devel
 */
public class ImageTrans {

    public static Mat escalar(Mat m, int esc) {
        float z = (float) esc / 10;
        Mat re = new Mat(m.rows(), m.cols(), m.type());
        double[] newSpec;
        int x, y;
        int dx = (int) (m.rows() - (m.rows() * z)) / 2;
        int dy = (int) (m.cols() - (m.cols() * z)) / 2;
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                newSpec = m.get(i, j);
                x = (int) (i * z) + dx;
                y = (int) (j * z) + dy;
                re.put(x, y, newSpec);
            }
        }
        return re;
    }

    public static Mat rotation(Mat m, int grd, double esc) {
        if (esc == 0) {
            esc = 1;
        }
        Mat re = new Mat(m.rows(), m.cols(), m.type());
        int dx = (int) (m.rows() - (m.rows() * esc)) / 2;
        int dy = (int) (m.cols() - (m.cols() * esc)) / 2;
        double[] newSpec;
        double x, y;
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                newSpec = m.get(i, j);
                x = ((i * cos(grd)) - (j * sin(grd)));
                y = ((i * sin(grd)) + (j * cos(grd)));
                x = (x * esc) + dx;
                y = (y * esc) + dy;
                re.put((int) abs(x), (int) abs(y), newSpec);
            }
        }
        return re;
    }
}
