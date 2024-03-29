/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_management;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.RenderedImage;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author devel
 */
public class ImageManager {

    private static String current_dir;

    public static Mat loadImage() {
        JFileChooser chooser = new JFileChooser("D:/Documentos/Procesamiento");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png");
        chooser.setFileFilter(filter);
        chooser.showDialog(null, null);
        File selectedFile = chooser.getSelectedFile();
        ImageManager.current_dir = selectedFile.getPath();
        Mat newImage = Imgcodecs.imread(chooser.getSelectedFile().getPath());
        return newImage;
    }

    public static Mat loadImageDir() {
        Mat newImage = Imgcodecs.imread(ImageManager.current_dir);
        return newImage;
    }

    public static byte[] toBytes(Mat image) {
        int bufferSize = (int) (image.total() * image.elemSize());
        byte[] buffer = new byte[bufferSize];
        return buffer;
    }

    public static Image toBufferedImage(Mat image, byte[] bytes) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (image.channels() > 1 && image.channels() <= 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        if (image.channels() > 3) {
            type = BufferedImage.TYPE_4BYTE_ABGR;
        }
        image.get(0, 0, bytes); // get all the pixels
        BufferedImage toImage = new BufferedImage(image.width(), image.height(), type);
        final byte[] targetPixels = ((DataBufferByte) toImage.getRaster().
                getDataBuffer()).getData();
        System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
        return toImage;
    }

    public static RenderedImage toRenderedImage(BufferedImage m) {
        Graphics2D mgra = m.createGraphics();
        mgra.drawImage(m, null, null);
        RenderedImage rimage = (RenderedImage) m;
        return rimage;
    }

    public static Mat detectObject(Mat img) {
        JFileChooser chooser = new JFileChooser("D:/Documentos/Procesamiento/deteccion/CarData/data");
        chooser.showDialog(null, null);

        File selectedFile = chooser.getSelectedFile();

        String cascadePath = selectedFile.getPath();
        CascadeClassifier objectDetector = new CascadeClassifier(cascadePath);
        img = detectAndDrawFace(img, objectDetector);
        return img;
    }

    private static Mat detectAndDrawFace(Mat image, CascadeClassifier objectDetector) {
        MatOfRect obectDetections = new MatOfRect();
        objectDetector.detectMultiScale(image, obectDetections);
        for (Rect rect : obectDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }

        return image;
    }
}
