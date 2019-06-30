/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_management;

import java.util.Arrays;
import org.opencv.core.Mat;

/**
 *
 * @author devel
 */
public class ImageFilters2 {
    
    public static Mat avergingFilter(byte bytes[], Mat m) {
        double newSpec[];
        Mat re = m.clone();
        for (int i = 1; i < m.rows() - 1; i++) {
            for (int j = 1; j < m.cols() - 1; j++) {
                newSpec = m.get(i, j);
                newSpec[0] = (newSpec[0] / 9.0) + ((m.get(i - 1, j - 1)[0]) / 9.0) + ((m.get(i - 1, j)[0]) / 9.0)
                        + ((m.get(i - 1, j + 1)[0]) / 9.0) + ((m.get(i, j - 1)[0]) / 9.0) + ((m.get(i, j + 1)[0]) / 9.0)
                        + ((m.get(i + 1, j - 1)[0]) / 9.0) + ((m.get(i + 1, j)[0]) / 9.0) + ((m.get(i + 1, j + 1)[0]) / 9.0);
                newSpec[1] = (newSpec[1] / 9.0) + ((m.get(i - 1, j - 1)[1]) / 9.0) + ((m.get(i - 1, j)[1]) / 9.0)
                        + ((m.get(i - 1, j + 1)[1]) / 9.0) + ((m.get(i, j - 1)[1]) / 9.0) + ((m.get(i, j + 1)[1]) / 9.0)
                        + ((m.get(i + 1, j - 1)[1]) / 9.0) + ((m.get(i + 1, j)[1]) / 9.0) + ((m.get(i + 1, j + 1)[1]) / 9.0);
                newSpec[2] = (newSpec[2] / 9.0) + ((m.get(i - 1, j - 1)[2]) / 9.0) + ((m.get(i - 1, j)[2]) / 9.0)
                        + ((m.get(i - 1, j + 1)[2]) / 9.0) + ((m.get(i, j - 1)[2]) / 9.0) + ((m.get(i, j + 1)[2]) / 9.0)
                        + ((m.get(i + 1, j - 1)[2]) / 9.0) + ((m.get(i + 1, j)[2]) / 9.0) + ((m.get(i + 1, j + 1)[2]) / 9.0);
                re.put(i, j, newSpec);
            }
        }
        return re;
    }
    
    public static Mat gaussianFilter(byte bytes[], Mat m) {
        double newSpec[];
        Mat re = m.clone();
        for (int i = 1; i < m.rows() - 1; i++) {
            for (int j = 1; j < m.cols() - 1; j++) {
                newSpec = m.get(i, j);
                newSpec[0] = (newSpec[0] / 4.0) + ((m.get(i - 1, j - 1)[0]) / 16.0) + ((m.get(i - 1, j)[0]) / 8.0)
                        + ((m.get(i - 1, j + 1)[0]) / 16.0) + ((m.get(i, j - 1)[0]) / 8.0) + ((m.get(i, j + 1)[0]) / 8.0)
                        + ((m.get(i + 1, j - 1)[0]) / 16.0) + ((m.get(i + 1, j)[0]) / 8.0) + ((m.get(i + 1, j + 1)[0]) / 16.0);
                newSpec[1] = (newSpec[1] / 4.0) + ((m.get(i - 1, j - 1)[1]) / 16.0) + ((m.get(i - 1, j)[1]) / 8.0)
                        + ((m.get(i - 1, j + 1)[1]) / 16.0) + ((m.get(i, j - 1)[1]) / 8.0) + ((m.get(i, j + 1)[1]) / 8.0)
                        + ((m.get(i + 1, j - 1)[1]) / 16.0) + ((m.get(i + 1, j)[1]) / 8.0) + ((m.get(i + 1, j + 1)[1]) / 16.0);
                newSpec[2] = (newSpec[2] / 4.0) + ((m.get(i - 1, j - 1)[2]) / 16.0) + ((m.get(i - 1, j)[2]) / 8.0)
                        + ((m.get(i - 1, j + 1)[2]) / 16.0) + ((m.get(i, j - 1)[2]) / 8.0) + ((m.get(i, j + 1)[2]) / 8.0)
                        + ((m.get(i + 1, j - 1)[2]) / 16.0) + ((m.get(i + 1, j)[2]) / 8.0) + ((m.get(i + 1, j + 1)[2]) / 16.0);
                re.put(i, j, newSpec);
            }
        }
        return re;
    }
    
    public static Mat medianFilter(byte bytes[], Mat m) {
        double newSpec[];
        Mat re = m.clone();
        int c1 = 0, c2 = 0, c3 = 0;
        double[] B = new double[9],
                G = new double[9],
                R = new double[9];
        for (int i = 1; i < m.rows() - 1; i++) {
            for (int j = 1; j < m.cols() - 1; j++) {
                newSpec = m.get(i, j);
                while (c1 < 8) {
                    B[c1] = (newSpec[0]);
                    c1++;
                    B[c1] = (m.get(i - 1, j - 1)[0]);
                    c1++;
                    B[c1] = (m.get(i - 1, j)[0]);
                    c1++;
                    B[c1] = (m.get(i - 1, j + 1)[0]);
                    c1++;
                    B[c1] = (m.get(i, j - 1)[0]);
                    c1++;
                    B[c1] = (m.get(i, j + 1)[0]);
                    c1++;
                    B[c1] = (m.get(i + 1, j - 1)[0]);
                    c1++;
                    B[c1] = (m.get(i + 1, j)[0]);
                    c1++;
                    B[c1] = (m.get(i + 1, j + 1)[0]);
                    c1++;
                }
                while (c2 < 8) {
                    G[c2] = (newSpec[1]);
                    c2++;
                    G[c2] = (m.get(i - 1, j - 1)[1]);
                    c2++;
                    G[c2] = (m.get(i - 1, j)[1]);
                    c2++;
                    G[c2] = (m.get(i - 1, j + 1)[1]);
                    c2++;
                    G[c2] = (m.get(i, j - 1)[1]);
                    c2++;
                    G[c2] = (m.get(i, j + 1)[1]);
                    c2++;
                    G[c2] = (m.get(i + 1, j - 1)[1]);
                    c2++;
                    G[c2] = (m.get(i + 1, j)[1]);
                    c2++;
                    G[c2] = (m.get(i + 1, j + 1)[1]);
                    c2++;
                }
                while (c3 < 8) {
                    R[c3] = (newSpec[2]);
                    c3++;
                    R[c3] = (m.get(i - 1, j - 1)[2]);
                    c3++;
                    R[c3] = (m.get(i - 1, j)[2]);
                    c3++;
                    R[c3] = (m.get(i - 1, j + 1)[2]);
                    c3++;
                    R[c3] = (m.get(i, j - 1)[2]);
                    c3++;
                    R[c3] = (m.get(i, j + 1)[2]);
                    c3++;
                    R[c3] = (m.get(i + 1, j - 1)[2]);
                    c3++;
                    R[c3] = (m.get(i + 1, j)[2]);
                    c3++;
                    R[c3] = (m.get(i + 1, j + 1)[2]);
                    c3++;
                }
                Arrays.sort(B);
                Arrays.sort(G);
                Arrays.sort(R);
                newSpec[0] = B[4];
                newSpec[1] = G[4];
                newSpec[2] = R[4];
                c1 = 0;
                c2 = 0;
                c3 = 0;
                re.put(i, j, newSpec);
            }
        }
        return re;
    }
}
