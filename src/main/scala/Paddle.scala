
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import util.Color
import scala.collection.mutable

/**
 * User: Paul Kohan
 * Date: 1/13/13
 * Time: 4:35 PM
 */

/**
 * Companion Object to hold width and height of a paddle
 */
object Paddle {
  val Width = 25
  val Height = 125
}

/**
 * Class representing a Pong paddle
 * @param init_x Starting x position
 * @param init_y Starting y position
 * @param c Color of the paddle
 * @param up Keyboard.Key that makes the paddle move up
 * @param down Keyboard.Key that makes the paddle move down
 */
case class Paddle(init_x:Int, init_y:Int, c:Color, up:Int, down:Int) extends DrawableObject(init_x,init_y, Paddle.Width, Paddle.Height) with Collidable {

  //speed in pixels/ms
  val speed = 0.75f

  /**
   * Draws the paddle onto the field
   */
  def draw() {
    // set the color of the quad (R,G,B,A)
    //TODO: maybe scala has a way to do auto parameter expansions, e.g. ruby hash parameter lists

    GL11.glColor4f(c.r, c.g, c.b, c.a)
    // draw quad
    GL11.glBegin(GL11.GL_QUADS)
    GL11.glVertex2f(x,y)
    GL11.glVertex2f(x + Paddle.Width,y)
    GL11.glVertex2f(x + Paddle.Width,y + Paddle.Height)
    GL11.glVertex2f(x,y + Paddle.Height)
    GL11.glEnd()
  }

  /**
   * Handles the keyboard input for the paddle and moves it accordingly
   */
  override def update(others:mutable.Queue[DrawableObject]) {
    super.update(others)
    val delta = getDelta
    if (Keyboard.isKeyDown(up)) moveUp(delta)
    else if (Keyboard.isKeyDown(down))  moveDown(delta)
  }

  /**
   * Moves the paddle up based on how many milliseconds have passed
   * @param delta number of milliseconds that have elapsed since the last frame
   */
  def moveUp(delta:Int) {
    if (boundingBox.top <= PongDisplay.Height) y += (delta * speed).toInt
  }

  /**
   * Moves the paddle down based on how many milliseconds have passed
   * @param delta number of milliseconds that have elapsed since the last frame
   */
  def moveDown(delta:Int) {
    if (boundingBox.bot >= 0) y -= (delta * speed).toInt
  }

  def onCollision(other: Collidable) {return} //Intentionally Blank
}
