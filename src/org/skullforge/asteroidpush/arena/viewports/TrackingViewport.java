package org.skullforge.asteroidpush.arena.viewports;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import org.skullforge.asteroidpush.arena.Entity;
import org.skullforge.asteroidpush.arena.Viewport;

public class TrackingViewport implements Viewport {

   public TrackingViewport(Entity tracked) {
      graphicsPort = null;
      trackedEntity = tracked;
      screenSize = new Vec2(0.0f, 0.0f);
      scale = new Vec2(12.5f, 12.5f);
   }

   @Override
   public void setGraphics(GameContainer container, Graphics g) {
      graphicsPort = g;
      screenSize.x = container.getWidth();
      screenSize.y = container.getHeight();
      graphicsPort.setBackground(bgColor);
      graphicsPort.scale(scale.x, scale.y);
   }

   @Override
   public void showImage(Image img, Vec2 origin, float rotation, Vec2 size) {
      graphicsPort.pushTransform();
      translateToTracked();
      
      img.setCenterOfRotation(size.x / 2.0f, size.y / 2.0f);
      img.setRotation(MathUtils.RAD2DEG * rotation);
      graphicsPort.drawImage(img,
                             origin.x - size.x / 2.0f,
                             origin.y - size.y / 2.0f,
                             origin.x + size.x / 2.0f,
                             origin.y + size.y / 2.0f,
                             0.0f,
                             0.0f,
                             img.getWidth(),
                             img.getHeight());

      graphicsPort.popTransform();
   }

   @Override
   public void drawPolygon(Polygon poly) {
      graphicsPort.pushTransform();
      translateToTracked();
      
      graphicsPort.setColor(Color.darkGray);
      graphicsPort.fill(poly);
      graphicsPort.setColor(Color.gray);
      graphicsPort.draw(poly);

      graphicsPort.popTransform();
   }

   private void translateToTracked() {
      Vec2 center = trackedEntity.getPosition();
      graphicsPort.translate(-center.x + screenSize.x/2.0f/scale.x, -center.y + screenSize.y/2.0f/scale.y);
   }
   
   private final Color bgColor = new Color(0.0f, 0.05f, 0.01f);
   private Graphics graphicsPort;
   private Entity trackedEntity;
   private Vec2 screenSize;
   private Vec2 scale;
}
