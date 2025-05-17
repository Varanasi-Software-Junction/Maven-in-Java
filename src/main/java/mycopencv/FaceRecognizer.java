package mycopencv;

import org.opencv.core.*;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.face.Face;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FaceRecognizer {

    public static void main(String[] args) {
        nu.pattern.OpenCV.loadLocally();
        CascadeClassifier faceDetector = new CascadeClassifier("xml/facedetect.xml");

        List<Mat> images = new ArrayList<>();
        List<Integer> labels = new ArrayList<>();

        File baseDir = new File("images");
        int label = 0;
        for (File personFolder : baseDir.listFiles()) {
            if (personFolder.isDirectory()) {
                for (File imgFile : personFolder.listFiles()) {
                    Mat img = Imgcodecs.imread(imgFile.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
                    MatOfRect faceDetections = new MatOfRect();
                    faceDetector.detectMultiScale(img, faceDetections);
                    for (Rect rect : faceDetections.toArray()) {
                        Mat face = new Mat(img, rect);
                        Imgproc.resize(face, face, new Size(200, 200));
                        images.add(face);
                        labels.add(label);
                    }
                }
                label++;
            }
        }

        LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
        recognizer.train(images, new MatOfInt(labels.stream().mapToInt(i -> i).toArray()));
        recognizer.save("face_model.xml");
        System.out.println("Model trained and saved.");

        // Test image
        Mat testImage = Imgcodecs.imread("images/test.jpg", Imgcodecs.IMREAD_GRAYSCALE);
        MatOfRect detected = new MatOfRect();
        faceDetector.detectMultiScale(testImage, detected);
        for (Rect rect : detected.toArray()) {
            Mat face = new Mat(testImage, rect);
            Imgproc.resize(face, face, new Size(200, 200));
            int[] labelPred = new int[1];
            double[] confidence = new double[1];
            recognizer.predict(face, labelPred, confidence);
            System.out.println("Predicted Label: " + labelPred[0] + " | Confidence: " + confidence[0]);
        }
    }
}
