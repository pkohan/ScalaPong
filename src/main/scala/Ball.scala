/**
 * User: Paul Kohan
 * Date: 1/13/13
 * Time: 7:52 PM
 */

import org.lwjgl.opengl.GL11
import util.Color

object Ball {
  val Speed = 0.75f
  val Radius = 10.0f
  val Roundness = 10
}

/**
 * Represents the ball in a Pong game
 * @param x Starting x position of the ball
 * @param y Starting y position of the ball
 * @param c Color of the ball
 */
class Ball(x:Int, y:Int, c:Color) extends DrawableObject {

  /**
   * Draws the ball on the field
   */
  def draw() {
    GL11.glColor4f(c.r, c.g, c.b, c.a)
    GL11.glPushMatrix()
    GL11.glTranslatef(x.toFloat, y.toFloat, 0)
    GL11.glScalef(Ball.Radius, Ball.Radius, 1)
    GL11.glBegin(GL11.GL_TRIANGLE_FAN)
    for (i <- (0 until Ball.Roundness)) {
      val angle = Math.PI * 2 * i / Ball.Roundness
      GL11.glVertex2f(Math.cos(angle).toFloat, Math.sin(angle).toFloat)
    }
    GL11.glEnd()
    GL11.glPopMatrix()
  }

  def update() {

  }
}
