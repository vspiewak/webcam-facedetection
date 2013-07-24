package main.scala

import org.opencv.core._
import org.opencv.objdetect.CascadeClassifier

class FaceDetector {

  val classifier = new CascadeClassifier(getClass().getResource("/classifiers/haarcascades/haarcascade_frontalface_alt_tree.xml").getPath())

  def detect(image: Mat) = {

    val faceDetections = new MatOfRect()
    classifier.detectMultiScale(image, faceDetections)

    //println("Detected %s faces".format(faceDetections.toArray().length))

    for (rect <- faceDetections.toArray) {
      Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255))
    }

  }

}
