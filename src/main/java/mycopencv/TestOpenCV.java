/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycopencv;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class TestOpenCV {
public static Mat loadImage(String imagePath) {
  Imgcodecs imgcodecs=  new Imgcodecs();
  Mat matrix =imgcodecs.imread(imagePath);
    return matrix;
}

public static void saveImage(Mat imageMatrix, String targetPath) {
    Imgcodecs.imwrite(targetPath, imageMatrix);
}
public static void faceDetect(Mat input)
{
    MatOfRect facesDetected = new MatOfRect();
    
     
CascadeClassifier classifier = new CascadeClassifier("xml/facedetect.xml");
MatOfRect faceDetections = new MatOfRect(); 
classifier.detectMultiScale(input, faceDetections);
 System.out.println(String.format("Detected %s faces", 
         faceDetections.toArray().length));
    
 
 
 for (Rect rect : faceDetections.toArray()) {
         Imgproc.rectangle(
            input,                                               // where to draw the box
            new Point(rect.x, rect.y),                            // bottom left
            new Point(rect.x + rect.width, rect.y + rect.height), // top right
            new Scalar(0, 0, 255),
            3                                                     // RGB colour
         );
      }
    saveImage(input, "images/out.png");
    
}
    public static void main(String[] args) {
//        OpenCV.loadShared();
nu.pattern.OpenCV.loadLocally();
        System.out.println("Hello Open CV");
        Mat m1=loadImage("images/faces.png");
        System.out.println(m1);
        faceDetect(m1);
        saveImage(m1, "images/write.png");
    }

}
