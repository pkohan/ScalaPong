/**
 * User: Paul Kohan
 * Date: 1/13/13
 * Time: 3:37 PM
 */

import org.lwjgl.{opengl, LWJGLException}
import opengl.{GL11, Display}
import scala.collection.mutable._

object PongDisplay {

  val drawQueue:scala.collection.mutable.Queue[DrawableObject] = new scala.collection.mutable.Queue[DrawableObject]

  def start() {
    try {
      Display.setDisplayMode(new opengl.DisplayMode(800,600))
      Display.create()
    } catch {
      case e:LWJGLException =>
        e.printStackTrace()
        sys.exit(0)
    }
    drawQueue.enqueue(new Paddle)
    GL11.glMatrixMode(GL11.GL_PROJECTION)
    GL11.glLoadIdentity()
    GL11.glOrtho(0, 800, 0, 600, 1, -1)
    GL11.glMatrixMode(GL11.GL_MODELVIEW)
    while (!Display.isCloseRequested) {
      // Clear the screen and depth buffer
      GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT)
      drawQueue.foreach(d => d.draw())
      Display.update()
    }
    Display.destroy()
  }

}
