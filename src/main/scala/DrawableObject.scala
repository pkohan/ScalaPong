import org.lwjgl.Sys
import scala.collection.mutable

/**
 * User: Paul Kohan
 * Date: 1/13/13
 * Time: 4:32 PM
 */

/**
 * Class Representing a object that can be drawn on the display
 * @param x X position of the object
 * @param y Y position of the object
 */
abstract class DrawableObject(var x:Int, var y:Int, var width:Int, var height:Int, var LastFrame:Long = Sys.getTime) {

  /**
   * Method to draw the object onto a display
   */
  def draw()

  /**
   * Method to update the object, where things such as input and movement are handled
   */
  def update(others:mutable.Queue[DrawableObject]) {
    if (this.isInstanceOf[Collidable]) {
      this.asInstanceOf[Collidable].updateBoundingBox()
      this.asInstanceOf[Collidable].checkCollisions(others)
    }
  }

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
