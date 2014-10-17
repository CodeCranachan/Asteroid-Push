package org.codecranachan.asteroidpush.base.ui.simulation;

import java.util.Collection;

import org.codecranachan.asteroidpush.base.visuals.Representation;
import org.codecranachan.asteroidpush.base.visuals.RepresentationRenderer;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class PilotUi implements Viewport {
   private RepresentationRenderer renderer;

   public PilotUi() {
      renderer = new RepresentationRenderer();
   }

   public void setRepresentations(Collection<Representation> representations) {
      renderer.setRepresentations(representations);
   }

   public void setFocus(Arrow focus) {
      renderer.setFocus(focus);
   }

   public void resize(Rectangle frame) {
      renderer.setFrame(frame);
   }

   public void render(Graphics g) {
      renderer.render(g);
   }

   public void update(GameContainer container, StateBasedGame game, int delta) {
   }

   public void setHover(float x, float y) {
   }

   public void resetHover() {
   }

   public void mousePressed(int button, int x, int y) {
   }

}
