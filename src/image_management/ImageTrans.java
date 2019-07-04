/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_management;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import org.opencv.core.Mat;

/**
 *
 * @author devel
 */
public class ImageTrans {

    public static Mat escalar(byte bytes[], Mat m, int esc) {
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

    public static Mat rotation(byte bytes[], Mat m, int grd) {
        
        
        
        Mat re = new Mat(m.rows(), m.cols(), m.type());
        double[] newSpec;
        float z1, z2;
        int x, y;
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                newSpec = m.get(i, j);
                x = Math.abs((int) ((i * cos(grd)) - (j * sin(grd))));
                y = Math.abs((int) ((i * sin(grd)) + (j * cos(grd))));
                re.put(x, y, newSpec);
            }
        }
        return re;
    }
}
