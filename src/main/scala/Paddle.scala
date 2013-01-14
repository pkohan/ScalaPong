
import org.lwjgl.input.Keyboard
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

class Paddle(var x:Int, var y:Int, c:Color, up:Int, down:Int) extends DrawableObject {

  //speed in pixels/ms
  val speed = 1.0f

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

  def update() {
    val delta = getDelta
    if (Keyboard.isKeyDown(up)) y += (delta * (speed)).toInt
    else if (Keyboard.isKeyDown(down))  y -= (delta * (speed)).toInt
  }

}
