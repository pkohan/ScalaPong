import org.lwjgl.opengl.GL11
import util.Color

/**
 * User: Paul Kohan
 * Date: 1/13/13
 * Time: 4:35 PM
 */

object Paddle {
  val Width = 50
  val Height = 200
}

class Paddle(x:Int, y:Int, c:Color) extends DrawableObject {

  /**
   * Draws the paddle onto the field
   */
  def draw() {
    // set the color of the quad (R,G,B,A)
    //TODO: find way to shorten this

    GL11.glColor4f(c.r, c.g, c.b, c.a)
    // draw quad
    GL11.glBegin(GL11.GL_QUADS)
    GL11.glVertex2f(x,y)
    GL11.glVertex2f(x + Paddle.Width,y)
    GL11.glVertex2f(x + Paddle.Width,y + Paddle.Height)
    GL11.glVertex2f(x,y + Paddle.Height)
    GL11.glEnd()
  }

}
