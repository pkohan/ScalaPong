import org.lwjgl.Sys

/**
 * User: Paul Kohan
 * Date: 1/13/13
 * Time: 4:32 PM
 */

/**
 * Class Representing a object that can be drawn on the display
 */
abstract class DrawableObject(var LastFrame:Long = Sys.getTime) {

  /**
   * Method to draw the object onto a display
   */
  def draw()

  /**
   * Method to update the object, where things such as input and movement are handled
   */
  def update()

  /**
   * Gets the current time from lwjgl in milliseconds
   * @return current system time in milliseconds
   */
  def getTime:Long = Sys.getTime * 1000 / Sys.getTimerResolution

  /**
   * Computes the number of seconds since the last frame update
   * @return number of seconds since last frame update
   */
  def getDelta:Int = {
    val time = getTime
    val delta = (getTime - LastFrame).toInt
    LastFrame = time
    delta
  }
}
