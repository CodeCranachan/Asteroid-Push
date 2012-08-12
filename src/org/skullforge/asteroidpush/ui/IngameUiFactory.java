package org.skullforge.asteroidpush.ui;

import java.util.Collection;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class IngameUiFactory implements UiFactory {

   @SuppressWarnings("unchecked")
   @Override
   public void createUi(Collection<Widget> widgetContainer) {
      try {
         UnicodeFont aFont = new UnicodeFont("Alfphabet-IV.ttf", 14, false, false);
         aFont.addAsciiGlyphs();
         aFont.getEffects().add(new ColorEffect(java.awt.Color.BLUE));
         aFont.loadGlyphs();
         // TODO: I want a position on the right of the screen, so how does the UiFactory get the dimensions of the screen
         Widget playerLabel = new Label(510.0f, 15.0f, "PlayerName", aFont);
         widgetContainer.add(playerLabel);
      } catch (SlickException s) {
         s.printStackTrace();
      }
   }
}
