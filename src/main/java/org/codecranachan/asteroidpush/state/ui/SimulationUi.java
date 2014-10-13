package org.codecranachan.asteroidpush.state.ui;

import java.util.Collection;
import java.util.LinkedList;

import org.codecranachan.asteroidpush.GameInstance;
import org.codecranachan.asteroidpush.ResourceLoader;
import org.codecranachan.asteroidpush.utils.Arrow;
import org.codecranachan.asteroidpush.visuals.Representation;
import org.codecranachan.asteroidpush.visuals.RepresentationRenderer;
import org.codecranachan.asteroidpush.visuals.actors.OriginRepresentation;
import org.codecranachan.asteroidpush.visuals.widget.BasicWidget;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class SimulationUi extends BasicWidget {
   private RepresentationRenderer renderer;
   private GameInstance game;

   static String defaultFontName = "resources/Alfphabet-IV.ttf";

   public SimulationUi(ResourceLoader loader) {
      renderer = new RepresentationRenderer();
      game = null;
   }

   public void setGame(GameInstance game) {
      assert game != null;
      this.game = game;
   }

   public void resize(Rectangle frame) {
      renderer.setFrame(frame);
   }

   public void render(Graphics g) {
      assert game != null;

      Collection<Representation> representations = new LinkedList<Representation>();
      representations.addAll(game.getRepresentations());
      representations.add(new OriginRepresentation());

      updateRendererFocus();

      g.setAntiAlias(true);
      renderer.setRepresentations(representations);
      renderer.render(g);
   }

   private void updateRendererFocus() {
      renderer.setFocus(new Arrow(new Vec2(0, 0), MathUtils.HALF_PI, 100));
   }

   public void update(GameContainer container, StateBasedGame game, int delta) {
   }

}
