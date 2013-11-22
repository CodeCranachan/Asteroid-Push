package org.skullforge.asteroidpush;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class ResourceLoader {
   public void setGameContainer(GameContainer container) {
      this.container = container;
   }
   
   @SuppressWarnings("unchecked")
   public Font loadFont(String filename, int size) {
      Font font;
      try {
         UnicodeFont unicodeFont = new UnicodeFont(filename, size, false, false);
         unicodeFont.addAsciiGlyphs();
         unicodeFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
         unicodeFont.loadGlyphs();
         font = unicodeFont;
      } catch (SlickException e) {
         font = container.getDefaultFont();
      } catch (RuntimeException e) {
         font = container.getDefaultFont();
      }
      return font;
   }
   
   private GameContainer container;
}
