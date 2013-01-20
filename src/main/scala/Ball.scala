/**
 * User: Paul Kohan
 * Date: 1/13/13
 * Time: 7:52 PM
 */

import org.lwjgl.opengl.GL11
import util.Color
import scala.util.Random
import scala.collection.mutable

object Ball {
  val MaxSpeed = 0.6f
  val Radius = 10.0f
  val Roundness = 10

  def diameter = Radius * 2

  def apply(x:Int, y:Int, c:Color):Ball = {
    val b = new Ball(x,y,c)
    b.initRandomVelocity()
    b
  }

}

/**
 * Represents the ball in a Pong game
 * @param init_x Starting x position of the ball
 * @param init_y Starting y position of the ball
 * @param c Color of the ball
 */
class Ball(init_x:Int, init_y:Int, c:Color) extends DrawableObject(init_x - Ball.Radius.toInt,init_y - Ball.Radius.toInt, Ball.diameter.toInt, Ball.diameter.toInt) with Collidable {

  var xVel:Float = 0.0f
  var yVel:Float = 0.0f
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

  private def initRandomVelocity() {
    val rand = new Random()
    val randAngle = Math.PI * 2 * rand.nextFloat() //TODO: make sure this doesn't go too parallel to paddles
    //println(randAngle)w
    xVel = Ball.MaxSpeed * Math.cos(randAngle).toFloat
    yVel = Ball.MaxSpeed * Math.sin(randAngle).toFloat
  }

  override def update(others:mutable.Queue[DrawableObject]) {
    super.update(others)
    val delta = getDelta
    if (x <= 10 || x >= PongDisplay.Width - 10) xVel = -xVel
    if (y <= 10 || y >= PongDisplay.Height - 10) yVel = -yVel
    x += (delta * xVel).toInt
    y += (delta * yVel).toInt
  }

  override def toString:String = "Ball: <X: " + x + ", Y:" + y + " >"

  def onCollision(other: Collidable) {
    other match {
      case _:Paddle =>
        println("COLLISION")
        xVel = -xVel
      case _ =>
    }
  }
}
