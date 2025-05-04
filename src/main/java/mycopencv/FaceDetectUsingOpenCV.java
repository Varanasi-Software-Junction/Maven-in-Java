/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycopencv;

import java.util.Scanner;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetectUsingOpenCV {

    private String inputfilename, outputfilename, fullinputpath, fulloutputpath;

    public FaceDetectUsingOpenCV(String inputfilename, String outputfilename) {
        this.inputfilename = inputfilename;
        this.outputfilename = outputfilename;
        this.fullinputpath = String.format("images/%s", inputfilename);
        this.fulloutputpath = String.format("images/%s", outputfilename);
        process();
    }

    private void process() {
        Mat m1 = loadImage();
        System.out.println(m1);
        faceDetect(m1);

    }

    public Mat loadImage() {
        Imgcodecs imgcodecs = new Imgcodecs();

        Mat matrix = Imgcodecs.imread(fullinputpath);
        return matrix;
    }

    public void saveImage(Mat imageMatrix) {

        Imgcodecs.imwrite(fulloutputpath, imageMatrix);
        System.out.printf("Input file %s, output file %s\n", fullinputpath, fulloutputpath);
    }

    public void faceDetect(Mat input) {

        CascadeClassifier classifier = new CascadeClassifier("xml/facedetect.xml");
        MatOfRect faceDetections = new MatOfRect();
        classifier.detectMultiScale(input, faceDetections);
        System.out.println(String.format("Detected %s faces",
                faceDetections.toArray().length));

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(
                    input, // where to draw the box
                    new Point(rect.x, rect.y), // bottom left
                    new Point(rect.x + rect.width, rect.y + rect.height), // top right
                    new Scalar(0, 0, 255),
                    3 // RGB colour
            );
        }
        System.out.println("Saving");
        saveImage(input);

    }

    public static void main(String[] args) {
//        OpenCV.loadShared();
        Scanner scanner = new Scanner(System.in);
        nu.pattern.OpenCV.loadLocally();
        System.out.println("Enter the input file name");
        String inputfilename = scanner.nextLine().toLowerCase().trim();
        System.out.println("Enter the output  file name");
        String outputfilename = scanner.nextLine().toLowerCase().trim();
        new FaceDetectUsingOpenCV(inputfilename, outputfilename);

    }

}
