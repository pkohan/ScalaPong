/**
 * User: Paul Kohan
 * Date: 1/20/13
 * Time: 12:48 PM
 */

object ScoreZone {
  val Width = 1
  val Height = 600
}

case class ScoreZone(init_x:Int, init_y:Int) extends DrawableObject(init_x,init_y,ScoreZone.Width,ScoreZone.Height) with Collidable {

  /**
   * Callback method to handle collision with another collidable object
   * @param other Object that collided with this
   */
  def onCollision(other: Collidable) {} //intentionally blank

  /**
   * Method to draw the object onto a display
   */
  def draw() {} //intentionally blank
}
