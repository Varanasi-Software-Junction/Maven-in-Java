/*
 * Basic Java program to detect faces in an image using OpenCV.
 * This version loads an image from the 'images/' folder, detects faces using a Haar Cascade Classifier,
 * draws rectangles around them, and saves the result as a new image.
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
    
    // Instance variables to hold the input/output file names and their full paths
    private String inputfilename, outputfilename, fullinputpath, fulloutputpath;

    /**
     * Constructor that receives input and output filenames, constructs full paths,
     * and then triggers the face detection process.
     */
    public FaceDetectUsingOpenCV(String inputfilename, String outputfilename) {
        this.inputfilename = inputfilename;
        this.outputfilename = outputfilename;
        
        // Create full file paths assuming images are stored in 'images/' folder
        this.fullinputpath = String.format("images/%s", inputfilename);
        this.fulloutputpath = String.format("images/%s", outputfilename);
        
        // Start the image processing (load image -> detect faces -> save result)
        process();
    }

    /**
     * Main logic of the class that loads image and applies face detection.
     */
    private void process() {
        // Load the image into memory
        Mat m1 = loadImage();
        System.out.println(m1); // Print basic matrix info for debugging
        
        // Perform face detection and save result
        faceDetect(m1);
    }

    /**
     * Loads an image from the disk into a Mat object (OpenCV matrix)
     */
    public Mat loadImage() {
        // Load the image in color format using OpenCV
        Mat matrix = Imgcodecs.imread(fullinputpath);
        return matrix;
    }

    /**
     * Saves a Mat image (possibly with rectangles drawn) back to disk.
     */
    public void saveImage(Mat imageMatrix) {
        Imgcodecs.imwrite(fulloutputpath, imageMatrix);
        System.out.printf("Input file %s, output file %s\n", fullinputpath, fulloutputpath);
    }

    /**
     * Detects faces in the provided image using Haar cascade,
     * draws red rectangles around them, and saves the output.
     */
    public void faceDetect(Mat input) {
        // Load the pre-trained Haar Cascade XML for face detection
        CascadeClassifier classifier = new CascadeClassifier("xml/facedetect.xml");
        
        // This object will hold all detected faces as rectangles
        MatOfRect faceDetections = new MatOfRect();
        
        // Apply the face detection on the input image
        classifier.detectMultiScale(input, faceDetections);
        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

        // Loop through each detected face and draw a rectangle around it
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(
                input, // the image to draw on
                new Point(rect.x, rect.y), // top-left point of rectangle
                new Point(rect.x + rect.width, rect.y + rect.height), // bottom-right point
                new Scalar(0, 0, 255), // red color in BGR format
                3 // thickness of the rectangle border
            );
        }

        // Save the image with rectangles drawn
        System.out.println("Saving");
        saveImage(input);
    }

    /**
     * Main method where execution begins. It loads OpenCV, takes user input,
     * and triggers the detection workflow.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Load the native OpenCV library using nu.pattern (platform-independent way)
        nu.pattern.OpenCV.loadLocally();
        
        // Prompt user for input file name
        System.out.println("Enter the input file name");
        String inputfilename = scanner.nextLine().toLowerCase().trim();
        
        // Prompt user for output file name
        System.out.println("Enter the output file name");
        String outputfilename = scanner.nextLine().toLowerCase().trim();
        
        // Call constructor to start face detection
        FaceDetectUsingOpenCV v = new FaceDetectUsingOpenCV(inputfilename, outputfilename);
    }

}
