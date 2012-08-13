package org.skullforge.asteroidpush.ui;

import org.skullforge.asteroidpush.appearances.Appearance;

/**
 * Encapsulates the drawing of world objects onto the screen. It is usually
 * passed Appearances and is expected to put them on the screen in an appealing
 * manner.
 * 
 * @author Konfuzzyus
 * 
 */
public interface Renderer {
   /**
    * Parse the Appearance provided and put it onto the screen.
    * 
    * @param appearance
    *           an appearance of any kind that needs to be drawn on screen.
    */
   void draw(Appearance appearance);
}
