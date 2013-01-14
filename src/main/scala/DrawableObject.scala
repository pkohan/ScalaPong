import org.lwjgl.Sys

/**
 * User: Paul Kohan
 * Date: 1/13/13
 * Time: 4:32 PM
 */
abstract class DrawableObject {

  var lastFrame = 0l
  /**
   * Method to draw the object onto a display
   */
  def draw()

  /**
   * Method to update the object, where things such as input and movement are handled
   */
  def update()

  def getTime:Long = Sys.getTime * 1000 / Sys.getTimerResolution

  def getDelta:Int = {
    val time = getTime
    val delta = (getTime - lastFrame).toInt
    lastFrame = time
    delta
  }
}
