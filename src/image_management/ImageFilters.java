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
}
