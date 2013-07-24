import javax.imageio.ImageIO
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.highgui.{VideoCapture, Highgui}

import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream


object Utils {

  def openWebcam(device: Int) = {

    val cap = new VideoCapture(0)

    if (!cap.isOpened()) {
      println("Webcam in use ...")
    } else {
      try {
        Thread.sleep(1500);
      } catch {
        case ie: InterruptedException => ie.printStackTrace()
      }

    }
    cap
  }

  def matToBuffer(mat: Mat): BufferedImage = {

    val matOfByte = new MatOfByte()
    Highgui.imencode(".jpg", mat, matOfByte)

    val byteArray = matOfByte.toArray()
    var bufImage: BufferedImage = null

    try {

      val in = new ByteArrayInputStream(byteArray)
      bufImage = ImageIO.read(in)

    } catch {
      case e: Exception => e.printStackTrace()
    }

    return bufImage;

  }

}
