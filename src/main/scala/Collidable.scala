import scala.collection.mutable

/**
 * User: Paul Kohan
 * Date: 1/19/13
 * Time: 7:43 PM
 */

/**
 * Designates a DrawableObject to be Collidable, and defines functions to detect and handle collisions
 * Expects implicit x,y,width,and height from DrawableObject
 */
trait Collidable {

  require(this.isInstanceOf[DrawableObject], "An object can only be collidable if it's drawable")

  implicit var x:Int
  implicit var y:Int
  implicit var width:Int
  implicit var height:Int
  var boundingBox:BoundingBox = BoundingBox(x,y,width,height)

  /**
   * Check for collisions with other collidable objects and call collision callback (onCollision)
   * @param drawQueue Queue of drawable objects in the game
   */
  def checkCollisions (drawQueue:mutable.Queue[DrawableObject]) {
    drawQueue.filter{ obj =>
      obj.isInstanceOf[Collidable] && obj.asInstanceOf[Collidable].isCollidingWith(this)
    }.foreach(obj => this.onCollision(obj.asInstanceOf[Collidable]))
  }

  /**
   * Returns true if this object is colliding with another object that isn't itself
   * @param other Object to check for collision with
   * @return True if colliding other, false otherwise
   */
  def isCollidingWith(other:Collidable) = !(this.boundingBox.bot > other.boundingBox.top ||
                                            this.boundingBox.top < other.boundingBox.bot ||
                                            this.boundingBox.left > other.boundingBox.right ||
                                            this.boundingBox.right < other.boundingBox.left) && !(other eq this)


  /**
   * Callback method to handle collision with another collidable object
   * @param other Object that collided with this
   */
  def onCollision(other:Collidable)

  /**
   * Updates the bounding box of each this object
   */
  def updateBoundingBox() {boundingBox = BoundingBox(x,y,width,height)}
}

/**
 * Class to represent the bounding box around a Collidable object
 * @param x Left coordinate of box
 * @param y Bottom coordinate of box
 * @param width Width of box
 * @param height Height of box
 */
class BoundingBox(x:Int, y:Int, width:Int, height:Int) {
  def top = y + height
  def bot = y
  def left = x
  def right = x + width

  override def toString = "Top: " + top + " Bottom: " + bot + " Left: " + left + " Right: " + right
}

/**
 * Companion object, defines an apply method for BoundingBox
 */
object BoundingBox {

  /**
   * Create BoundingBox with given parameters and return it
   * @param x Left coordinate of box
   * @param y Bottom coordinate of box
   * @param width Width of box
   * @param height Height of box
   * @return New BoundingBox with given parameters
   */
  def apply(x:Int, y:Int, width:Int, height:Int): BoundingBox = new BoundingBox(x,y,width,height)
}
