/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_management;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author devel
 */
public class ImageDetectionFunctions extends Imgproc {

    public static Mat sobel(Mat m) {
        Mat re = new Mat(m.rows(), m.cols(), m.type());
        Sobel(m, re, -1, 0, 1);
        return re;
    }

    public static Mat laplace(Mat m) {
        Mat re = new Mat(m.rows(), m.cols(), m.type());
        Laplacian(m, re, -1);
        return re;
    }

    public static Mat canny(Mat m) {
        Mat re = new Mat(m.rows(), m.cols(), m.type());
        Canny(m, re, 10, 50);
        return re;
    }
    
    public static Mat fourier(Mat m){
        Mat re = new Mat(m.rows(), m.cols(), m.type());
        Core.dft(m, re);
        return re;
    }
}
