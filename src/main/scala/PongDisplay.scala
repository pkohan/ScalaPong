/**
 * User: Paul Kohan
 * Date: 1/13/13
 * Time: 3:37 PM
 */

import org.lwjgl.input.Keyboard
import org.lwjgl.{Sys, opengl, LWJGLException}
import opengl.{GL11, Display}
import util.Color

/**
 * Singleton that represents the main game display, contains the main game loop
 */
object PongDisplay {

  val drawQueue:scala.collection.mutable.Queue[DrawableObject] = new scala.collection.mutable.Queue[DrawableObject]
  val Width = 800
  val Height = 600
  val StartTime = Sys.getTime
  /**
   * Creates the display and runs the game loop
   * TODO: Move game loop to its own method
   */
  def start() {
    try {
      Display.setDisplayMode(new opengl.DisplayMode(Width,Height))
      Display.create()
    } catch {
      case e:LWJGLException =>
        e.printStackTrace()
        sys.exit(0)
    }

    //Initialize drawable objects and put them in the draw queue
    drawQueue.enqueue(new Paddle(100, 100, new Color(0.0f, 0.5f, 0.0f), Keyboard.KEY_UP, Keyboard.KEY_DOWN))
    drawQueue.enqueue(new Paddle(650, 100, new Color(0.5f, 0.0f, 0.0f), Keyboard.KEY_W, Keyboard.KEY_S))
    drawQueue.enqueue(Ball(400, 400, new Color(0.0f, 0.0f, 0.5f)))
    drawQueue.enqueue(new ScoreZone(1,0))
    drawQueue.enqueue(new ScoreZone(799, 0))

    GL11.glMatrixMode(GL11.GL_PROJECTION)
    GL11.glLoadIdentity()
    GL11.glOrtho(0, Width, 0, Height, 1, -1)
    GL11.glMatrixMode(GL11.GL_MODELVIEW)

    while (!Display.isCloseRequested) {
      // Clear the screen and depth buffer
      GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT)
      if (drawQueue.filter(_.isInstanceOf[Ball]).length == 0) drawQueue.enqueue(Ball(400,400, new Color(0.0f, 0.0f, 0.5f)))
      drawQueue.foreach(_.update(drawQueue))
      drawQueue.dequeueAll(_.deleteMe) //remove deleted objects
      drawQueue.foreach(_.draw())
      Display.update()
      Display.sync(60)
    }
    Display.destroy()
  }

}
