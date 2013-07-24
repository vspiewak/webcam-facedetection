import main.scala.FaceDetector
import org.opencv.highgui.VideoCapture
import scala.concurrent.ops._
import scala.swing.Panel
import java.awt.Graphics2D
import org.opencv.core.Mat
import java.awt.image.BufferedImage

class WebcamPanel(capture: VideoCapture) extends Panel {

  protected var buffer: BufferedImage = captureImage()

  def faceDetector = new FaceDetector()

  override def paint(g: Graphics2D) {
    super.paint(g)

    drawCamera(g);

  }

  def captureImage(): BufferedImage = {
    val image = new Mat()
    capture.read(image)
    faceDetector.detect(image)
    Utils.matToBuffer(image)
  }

  def drawCamera(g: Graphics2D) {

    g.drawImage(buffer, 0, 0, null)
    g.dispose()

  }

  def run() {
    def fps = 10
    spawn {
      while (capture.isOpened) {
        synchronized {
          Thread.sleep(100)
          this.repaint()
        }
        buffer = captureImage()
      }
    }
  }

}