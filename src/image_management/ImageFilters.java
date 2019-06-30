/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_management;

import static java.lang.Math.pow;
import org.opencv.core.Mat;

/**
 *
 * @author devel
 */
public class ImageFilters {

    public static Mat addBrightness(byte bytes[], Mat image, int level) {
        if (level <= 0) {
            level = level * (-1);
        }
        double[] newSpec;
        for (int i = 0; i < image.rows(); i++) {
            for (int j = 0; j < image.cols(); j++) {
                newSpec = image.get(i, j);
                newSpec[0] = newSpec[0] + level;
                newSpec[1] = newSpec[1] + level;
                newSpec[2] = newSpec[2] + level;
                image.put(i, j, newSpec);
            }
        }
        return image;
    }

    public static Mat lessBrightness(byte bytes[], Mat image, int level) {
        if (level <= 0) {
            level = level * (-1);
        }
        double[] newSpec;
        for (int i = 0; i < image.rows(); i++) {
            for (int j = 0; j < image.cols(); j++) {
                newSpec = image.get(i, j);
                newSpec[0] = newSpec[0] - level;
                newSpec[1] = newSpec[1] - level;
                newSpec[2] = newSpec[2] - level;
                image.put(i, j, newSpec);
            }
        }
        return image;
    }

    public static Mat addGamma(byte bytes[], Mat m, int level) {
        if (level <= 0) {
            level = level * (-1);
        }
        double R = (level / 10.0);
        double[] newSpec;
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                newSpec = m.get(i, j);
                newSpec[0] = newSpec[0] + pow(newSpec[0], R);
                newSpec[1] = newSpec[1] + pow(newSpec[1], R);
                newSpec[2] = newSpec[2] + pow(newSpec[2], R);
                m.put(i, j, newSpec);
            }
        }
        return m;
    }

    public static Mat addContrast(byte bytes[], Mat m, int level) {
        if (level <= 0) {
            level = level * (-1);
        }
        double[] newSpec;
        int B = 127;
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                newSpec = m.get(i, j);
                if (newSpec[0] >= B) {
                    newSpec[0] = newSpec[0] + level;
                } else {
                    newSpec[0] = newSpec[0] - level;
                }
                if (newSpec[1] >= B) {
                    newSpec[1] = newSpec[1] + level;
                } else {
                    newSpec[1] = newSpec[1] - level;
                }
                if (newSpec[2] >= B) {
                    newSpec[2] = newSpec[2] + level;
                } else {
                    newSpec[2] = newSpec[2] - level;
                }
                m.put(i, j, newSpec);
            }
        }
        return m;
    }

    public static Mat lessContrast(byte bytes[], Mat m, int level) {
        if (level <= 0) {
            level = level * (-1);
        }
        double[] newSpec;
        int B = 127;
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                newSpec = m.get(i, j);
                if (newSpec[0] > B) {
                    newSpec[0] = newSpec[0] - level;
                } else {
                    newSpec[0] = newSpec[0] + level;
                }
                if (newSpec[1] > B) {
                    newSpec[1] = newSpec[1] - level;
                } else {
                    newSpec[1] = newSpec[1] + level;
                }
                if (newSpec[2] > B) {
                    newSpec[2] = newSpec[2] - level;
                } else {
                    newSpec[2] = newSpec[2] + level;
                }
                m.put(i, j, newSpec);
            }
        }
        return m;
    }

    public static Mat binarization(byte bytes[], Mat m) {
        double[] newSpec;
        double B = 127.0;
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                newSpec = m.get(i, j);
                double mayor = 0.0;
                if (newSpec[0] > newSpec[1]) {
                    if (newSpec[1] > newSpec[2]) {
                        mayor = newSpec[0];
                    } else {
                        mayor = newSpec[2];
                    }
                } else {
                    if (newSpec[1] > newSpec[2]) {
                        mayor = newSpec[1];
                    } else {
                        mayor = newSpec[2];
                    }
                }
                if (mayor < B) {
                    newSpec[0] = 0;
                    newSpec[1] = 0;
                    newSpec[2] = 0;
                } else {
                    newSpec[0] = 255;
                    newSpec[1] = 255;
                    newSpec[2] = 255;
                }
                m.put(i, j, newSpec);
            }
        }
        return m;
    }

    public static Mat negative(byte bytes[], Mat m) {
        double[] newSpec;
        for (int i = 0; i < m.rows(); i++) {
            for (int j = 0; j < m.cols(); j++) {
                newSpec = m.get(i, j);
                newSpec[0] = 255 - newSpec[0];
                newSpec[1] = 255 - newSpec[1];
                newSpec[2] = 255 - newSpec[2];
                m.put(i, j, newSpec);
            }
        }
        return m;
    }
}
