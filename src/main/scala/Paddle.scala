import org.lwjgl.opengl.GL11

/**
 * User: Paul Kohan
 * Date: 1/13/13
 * Time: 4:35 PM
 */
class Paddle extends DrawableObject {
  /**
   * Draws the paddle onto the field
   */
  def draw() {
    // set the color of the quad (R,G,B,A)
    GL11.glColor3f(0.5f,0.5f,1.0f)
    // draw quad
    GL11.glBegin(GL11.GL_QUADS)
    GL11.glVertex2f(100,100)
    GL11.glVertex2f(100+200,100)
    GL11.glVertex2f(100+200,100+200)
    GL11.glVertex2f(100,100+200)
    GL11.glEnd()
  }
}
