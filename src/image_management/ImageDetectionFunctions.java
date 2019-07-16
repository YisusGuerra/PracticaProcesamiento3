/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_management;

import java.awt.List;
import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
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

    public static Mat fourier(Mat m) {
        Mat re = new Mat();
        Mat gray = new Mat();
        Mat floatGray = new Mat();
        Mat ultima = new Mat();
        Mat otra = new Mat();
        Mat magnitud = new Mat();
        Mat tmp = new Mat();
        ArrayList<Mat> matList = new ArrayList<>();
        ArrayList<Mat> splitted = new ArrayList<>();
        cvtColor(m, gray, COLOR_RGB2GRAY);
        gray.convertTo(floatGray, CvType.CV_32FC1);
        matList.add(floatGray);
        Mat zeroMat = Mat.zeros(floatGray.size(), CvType.CV_32F);
        matList.add(zeroMat);
        Core.merge(matList, ultima);
        Core.dft(ultima, otra);
        Core.split(otra, splitted);
        Core.magnitude(splitted.get(0), splitted.get(1), magnitud);
        Core.add(Mat.ones(magnitud.size(), CvType.CV_32F), magnitud, magnitud);
        Core.log(magnitud, magnitud);
        int cx = magnitud.cols()/2;
        int cy = magnitud.rows()/2;
        Mat q0 = new Mat(magnitud, new Rect(0, 0, cx, cy));
        Mat q1 = new Mat(magnitud, new Rect(cx, 0, cx, cy));
        Mat q2 = new Mat(magnitud, new Rect(0, cy, cx, cy));
        Mat q3 = new Mat(magnitud, new Rect(cx, cy, cx, cy));
        q0.copyTo(tmp);
        q3.copyTo(q0);
        tmp.copyTo(q3);
        q1.copyTo(tmp);
        q2.copyTo(q1);
        tmp.copyTo(q2);
        magnitud.convertTo(magnitud, CvType.CV_8UC1);
        Core.normalize(magnitud, magnitud, 0, 255, Core.NORM_MINMAX, CvType.CV_8UC1);
        return magnitud;
    }
}
