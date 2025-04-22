/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 
package mycopencv;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;

public class WebCameraFaceDetect {

    public static void faceDetectFromWebcam() {
        // Load the face detection classifier
        CascadeClassifier classifier = new CascadeClassifier("xml/facedetect.xml");

        // Open the default webcam (camera index 0)
        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("Error: Cannot open camera");
            return;
        }

        Mat frame = new Mat();
        MatOfRect faceDetections = new MatOfRect();

        System.out.println("Press ESC to exit...");

        while (true) {
            // Read a new frame from the webcam
            if (!camera.read(frame)) {
                System.out.println("Failed to read frame from camera");
                break;
            }

            // Detect faces
            classifier.detectMultiScale(frame, faceDetections);

            for (Rect rect : faceDetections.toArray()) {
                Imgproc.rectangle(frame,
                        new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 0, 255),
                        2);
                Imgproc.putText(frame, "Face", new Point(rect.x, rect.y - 5),
                        Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 255, 0), 2);
            }

            // Show the frame in a window
            HighGui.imshow("Webcam Face Detection", frame);

            // Wait for ESC key to exit
            if (HighGui.waitKey(30) == 27) {
                break;
            }
        }

        camera.release();
        HighGui.destroyAllWindows();
    }

    public static void main(String[] args) {
        nu.pattern.OpenCV.loadLocally();  // Load OpenCV native library
        System.out.println("Starting webcam face detection...");
        faceDetectFromWebcam();
    }
}
