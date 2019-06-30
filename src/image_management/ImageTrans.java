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
        int dx = (int) (m.rows() - m.rows() * z);
        int dy = (int) (m.cols() - m.cols() * z);
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                newSpec = m.get(i, j);
                x = (int) (i * z) + dx;
                y = (int) (j * z) + dy;
                re.put(i, j, newSpec);
            }
        }
        return re;
    }

    public static Mat rotation(byte bytes[], Mat m, int esc) {
        Mat re = new Mat(m.rows(), m.cols(), m.type());
        double[] newSpec;
        int x, y;
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                newSpec = m.get(i, j);
                x = (int) ((i * cos(esc)) - (j * sin(esc)));
                y = (int) ((i * sin(esc)) + (j * cos(esc)));
                re.put(i, j, newSpec);
            }
        }
        return re;
    }
}
