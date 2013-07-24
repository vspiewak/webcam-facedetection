import java.awt.Dimension
import org.opencv.core.Core
import org.opencv.highgui.{Highgui, VideoCapture}
import scala.swing.{BorderPanel, MainFrame, SimpleSwingApplication}

object App extends SimpleSwingApplication {

  override def main(args: Array[String]) = {

    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    super.main(args)

  }

  def top = new MainFrame() {


    val cap: VideoCapture = Utils.openWebcam(0)
    val width = cap.get(Highgui.CV_CAP_PROP_FRAME_WIDTH)
    val height = cap.get(Highgui.CV_CAP_PROP_FRAME_HEIGHT)

    println(String.format("Webcam resolution: %s x %s", width.toString, height.toString))

    val panel = new WebcamPanel(cap)

    title = "First Swing App"
    contents = ui(panel)
    size = new Dimension(width.toInt, height.toInt)
    visible = true

    panel.run()

  }

  def ui(panel: WebcamPanel) = new BorderPanel {
    add(panel, BorderPanel.Position.Center)
  }

}