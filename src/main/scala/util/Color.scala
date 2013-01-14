package util

/**
 * User: Paul Kohan
 * Date: 1/13/13
 * Time: 5:12 PM
 */

/**
 * Class representing a color with RGB and alpha attributes
 * @param r Float representing red (0.0f - 1.0f)
 * @param g Float representing blue (0.0f - 1.0f)
 * @param b Float representing green (0.0f - 1.0f)
 * @param a Float representing alpha (0.0f - 1.0f)
 */
class Color(val r:Float, val g:Float, val b:Float, val a:Float) {
  /**
   * Class representing a color with RGB and alpha attributes
   * @param r Float representing red (0.0f - 1.0f)
   * @param g Float representing blue (0.0f - 1.0f)
   * @param b Float representing green (0.0f - 1.0f)
   */
  def this(r:Float, g:Float, b:Float) { this(r,g,b,1.0f) }
}
