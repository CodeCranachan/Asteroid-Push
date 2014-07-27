//    Asteroid Push - A game featuring selfmade spaceships and pompous physics
//    Copyright (C) 2013  Christian Meyer, Silvan Wegmann
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.codecranachan.asteroidpush;

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
